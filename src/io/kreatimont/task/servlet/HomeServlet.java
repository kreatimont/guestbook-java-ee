package io.kreatimont.task.servlet;

import io.kreatimont.task.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        req.setAttribute("user", user);
        req.getSession().removeAttribute("user");
        this.getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
