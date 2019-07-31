package ru.shangareev.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name="mainServlet", urlPatterns = {"/main"})
public class MainServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(MainServlet.class);


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("MainServlet request");
        resp.setContentType(req.getAttribute("contentType").toString());

        resp.getWriter().println("<h2>Main Servlet</h2>");
        req.getRequestDispatcher("/WEB-INF/views/main.html").forward(req, resp);
    }
}
