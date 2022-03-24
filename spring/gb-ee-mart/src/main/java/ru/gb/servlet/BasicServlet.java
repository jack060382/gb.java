package ru.gb.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;


public class BasicServlet implements Servlet {

    private static Logger logger = (Logger) LoggerFactory.getLogger(BasicServlet.class);

    // transient - non serializible
    private transient ServletConfig config;


    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("New request.");

        servletResponse.getWriter().println("<h1>Servler content</h1>");
    }

    @Override
    public String getServletInfo() {
        return "Basic Servlet";
    }

    @Override
    public void destroy() {
        logger.info("Servlet destroyed.");
    }
}
