package com.gordonfromblumberg.auth.servlet;

/**
 * Copyright (c) 2019 Gordon from Blumberg. All Rights Reserved.
 * <p>
 * Project: Authentication
 *
 * @author: Aleksandr Ivko
 * Created: 13.07.19
 */

import com.gordonfromblumberg.auth.core.entity.User;
import com.gordonfromblumberg.common.db.EntityManagerProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Metamodel;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "test", value = "/test")
public class TestServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(TestServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("doGet: log test request");

        EntityManager em = new EntityManagerProvider().getEntityManager();

        logger.debug("doGet: EntityManager = {}", em);

        Metamodel mm = em.getMetamodel();

        logger.debug("doGet: entity type = {}", mm.entity(User.class));

        resp.getWriter().println("Hello from TestServlet");
        resp.getWriter().flush();
    }
}
