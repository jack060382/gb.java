package main.servlet;

import main.servlet.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet(name="Main", urlPatterns = "")
public class Main implements Servlet {

    private static Logger logger = (Logger) LoggerFactory.getLogger(Main.class);


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
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        logger.info("New request.");

        List<Product> products = List.of(
                new Product(1, "Sahar", 123.89),
                new Product(2, "Sol", 123.89),
                new Product(3, "Spichki", 123.89),
                new Product(4, "Hleb", 123.89),
                new Product(5, "Patrony", 123.89)
        );

        req.setAttribute("products", products);
        req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
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
