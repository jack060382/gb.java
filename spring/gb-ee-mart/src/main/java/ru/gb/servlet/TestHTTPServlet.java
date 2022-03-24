package ru.gb.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name="TestHTTPServlet", urlPatterns = "/test_http_servlet")
public class TestHTTPServlet extends HttpServlet {
    private static Logger logger = (Logger) LoggerFactory.getLogger(TestHTTPServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New GET request");
        logger.info(req.getContextPath());
        logger.info(req.getServletPath());
        logger.info(req.getQueryString());
        logger.info(req.getHeader("User-agent"));

        resp.setHeader("Content-type", "text/html; charset=utf-8");
        resp.getWriter().print("<h1>New get request</h1>");
        resp.getWriter().printf("<h2>param1 = %s, param2 = %s</h2>"
                , req.getParameter("param1")
                , req.getParameter("param2"));

        //getServletContext().getRequestDispatcher("/basic_servlet").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New POST request");

        resp.getWriter().printf("<h2>param1: %s</h2>", readAllLines(req.getReader()));
    }

    private String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }
        return content.toString();
    }



}
