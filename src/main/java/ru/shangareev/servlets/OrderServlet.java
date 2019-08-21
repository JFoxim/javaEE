package ru.shangareev.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name="orderServlet", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(OrderServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("OrderServlet request");
        //resp.setContentType(req.getAttribute("contentType").toString());

        //req.setAttribute("title", "Заказы");
        req.getRequestDispatcher("WEB-INF/views/order.xhtml").forward(req, resp);

    }
}
