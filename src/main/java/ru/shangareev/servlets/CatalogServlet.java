package ru.shangareev.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shangareev.persist.Product;
import ru.shangareev.persist.ProductRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name="catalogServlet", urlPatterns = {"/catalog"})
public class CatalogServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(CatalogServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("CatalogServlet request");
        resp.setContentType(req.getAttribute("contentType").toString());

        ServletContext context=getServletContext();

        ProductRepository productRepository = (ProductRepository)context.getAttribute("productRepository");

        List<Product> productList = new ArrayList<>();

        try {
              productList = productRepository.getAllProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("productList", productList);
        req.setAttribute("title", "Каталог");

        req.getRequestDispatcher("/WEB-INF/views/catalog.jsp").forward(req, resp);
    }
}
