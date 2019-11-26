package com.gordonfromblumberg.auth.core.filter;

import com.gordonfromblumberg.auth.core.AuthContextHolder;
import com.gordonfromblumberg.auth.core.entity.Session;
import com.gordonfromblumberg.auth.core.entity.User;
import com.gordonfromblumberg.common.db.EntityManagerProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebFilter("/*")
public class AuthFilter implements Filter {
    private static final String ANONYMOUS_NAME = "anonymous";
    private static final String SESSION_ATTRIBUTE = "GFB_SESSION";
    private static final String SESSION_ID_COOKIE = "GFB_SESSION_ID";

    private static User anonymousUser;

    private EntityManager entityManager;

    private static final Logger logger = LogManager.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        anonymousUser = new User();
        anonymousUser.setLogin(ANONYMOUS_NAME);

        entityManager = EntityManagerProvider.getEntityManager();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpRequest.getSession();

        UUID sessionIdFromCookie = getSessionIdFromCookie(httpRequest);
        Object sessionFromHttpSession = httpSession.getAttribute(SESSION_ATTRIBUTE);

        Session gfbSession;

        if (sessionFromHttpSession != null) {
            if (!(sessionFromHttpSession instanceof Session)) {
                throw new IllegalStateException( String.format("Session attribute %s contains object of class %s",
                        SESSION_ATTRIBUTE, sessionFromHttpSession.getClass()) );
            }

            gfbSession = (Session) sessionFromHttpSession;
            logger.debug("current session keeps session {}", sessionFromHttpSession);

            if (!gfbSession.getSessionId().equals(sessionIdFromCookie)) {
                throw new IllegalStateException( String.format("GfB session %s in http session does not match cookie %s",
                        gfbSession.getSessionId(), sessionIdFromCookie) );
            }

        } else if (sessionIdFromCookie != null) {

            logger.debug("cookie contains session id {}, but current http session does not, so set it");
            gfbSession = loadSession(sessionIdFromCookie);
            logger.debug("set to http session attribute {} to session {}", SESSION_ATTRIBUTE, gfbSession.getSessionId());
            httpSession.setAttribute(SESSION_ATTRIBUTE, gfbSession);

        } else {

            logger.debug("neither http session nor cookie contains gfb session");
            gfbSession = getAnonymousSession();
            setSessionIdCookieToResponse(gfbSession.getSessionId(), (HttpServletResponse) response);

        }

        try {

            AuthContextHolder.setCurrentGfbSession(gfbSession);
            chain.doFilter(httpRequest, response);

        } finally {
            AuthContextHolder.clear();
        }
    }

    @Override
    public void destroy() {

    }

    private UUID getSessionIdFromCookie(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if (SESSION_ID_COOKIE.equals(cookie.getName())) {
                return UUID.fromString(cookie.getValue());
            }
        }

        return null;
    }

    private Session loadSession(UUID sessionId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Session> criteriaQuery = criteriaBuilder.createQuery(Session.class);
        Root<Session> fromSession = criteriaQuery.from(Session.class);
        ParameterExpression<UUID> par = criteriaBuilder.parameter(UUID.class);
        criteriaQuery.select(fromSession).where(criteriaBuilder.equal(fromSession.get("sessionId"), par));
        TypedQuery<Session> query = entityManager.createQuery(criteriaQuery);
        query.setParameter(par, sessionId);
        return query.getSingleResult();
    }

    private Session getAnonymousSession() {
        return entityManager.find(Session.class, 1L);
    }

    private void setSessionIdCookieToResponse(UUID sessionId, HttpServletResponse response) {
        Cookie cookie = new Cookie(SESSION_ID_COOKIE, sessionId.toString());
        cookie.setHttpOnly(true);
        // cookie.setSecure(true);
        cookie.setPath("");
        response.addCookie(cookie);
    }
}