package ru.shangareev.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shangareev.entities.Product;
import ru.shangareev.repositories.ProductRepository;
import ru.shangareev.services.ProductService;

import javax.inject.Inject;
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


    @Inject
    private ProductService productService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("CatalogServlet request");

        //ServletContext context = getServletContext();

        //ProductRepository productRepository = (ProductRepository)context.getAttribute("productRepository");

        List<Product> productList = new ArrayList<>();

        productList = productService.getProductList();


        //req.setAttribute("productList", productList);
        req.getRequestDispatcher("WEB-INF/views/catalog.xhtml").forward(req, resp);
    }
}
