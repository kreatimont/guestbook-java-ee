package io.kreatimont.task;


import io.kreatimont.task.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = Validator.checkUser(email, password);
        if(user != null) {
            req.getSession().setAttribute("user", user);
            req.setAttribute("user",user);
            req.getRequestDispatcher("home.jsp").forward(req,resp);
        } else {
            req.setAttribute("status","failed");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }

    }
}
