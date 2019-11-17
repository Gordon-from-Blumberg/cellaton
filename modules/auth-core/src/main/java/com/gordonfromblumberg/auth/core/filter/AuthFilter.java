package com.gordonfromblumberg.auth.core.filter;

import com.gordonfromblumberg.auth.core.AuthContextHolder;
import com.gordonfromblumberg.auth.core.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    private static final String ANONYMOUS_NAME = "anonymous";
    private static final String SESSION_USER_ATTRIBUTE = "SESSION_USER";

    private static User anonymousUser;

    private static final Logger logger = LogManager.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        anonymousUser = new User();
        anonymousUser.setLogin(ANONYMOUS_NAME);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        Object sessionUserFromAttribute = session.getAttribute(SESSION_USER_ATTRIBUTE);
        User user;

        if (sessionUserFromAttribute != null) {
            if (!(sessionUserFromAttribute instanceof User)) {
                throw new IllegalStateException( String.format("Session attribute %s contains object of class %s",
                        SESSION_USER_ATTRIBUTE, sessionUserFromAttribute.getClass()) );
            }

            user = (User) sessionUserFromAttribute;
            logger.debug("current session keeps user {}", sessionUserFromAttribute);

        } else {
            logger.debug("current session does not keep a user, so set anonymous");
            user = anonymousUser;
            session.setAttribute(SESSION_USER_ATTRIBUTE, user);
        }

        try {
            AuthContextHolder.setCurrentUser(user);
            chain.doFilter(httpRequest, response);

        } finally {
            AuthContextHolder.clear();
        }
    }

    @Override
    public void destroy() {

    }
}