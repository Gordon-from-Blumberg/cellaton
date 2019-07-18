package com.gordonfromblumberg.auth.servlet;

/**
 * Copyright (c) 2019 Gordon from Blumberg. All Rights Reserved.
 * <p>
 * Project: Cellaton
 *
 * @author: Aleksandr Ivko
 * Created: 13.07.19
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        logger.debug("doGet: system property logDir = {}", System.getProperty("logDir"));
        logger.debug("doGet: system property = {}", System.getProperties());
        resp.getWriter().println("Hello from TestServlet");
        resp.getWriter().flush();
    }
}