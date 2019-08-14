package ru.shangareev.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name="ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("ProductServlet request");
//        resp.setContentType(req.getAttribute("contentType").toString());
//
//        req.setAttribute("title", "Продукты");

        req.getRequestDispatcher("WEB-INF/views/product.xhtml").forward(req, resp);

    }
}
