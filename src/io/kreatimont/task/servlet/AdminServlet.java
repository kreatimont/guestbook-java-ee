package io.kreatimont.task.servlet;


import io.kreatimont.task.model.User;
import io.kreatimont.task.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        req.getRequestDispatcher("admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = Validator.checkUser(email, password);

        if(user != null) {

            if (user.getRole().equals("admin")) {
                req.getRequestDispatcher("admin.jsp").forward(req,resp);
            } else {
                req.setAttribute("status","failed");
                req.getRequestDispatcher("index.jsp").forward(req,resp);
            }
        } else {
            req.setAttribute("status","failed");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }

    }
}
