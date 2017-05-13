package io.kreatimont.task.servlet;


import io.kreatimont.task.model.User;
import io.kreatimont.task.utils.Validator;

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

            if (user.getRole().equals("admin")) {
                resp.sendRedirect("admin.jsp");
            } else {
                resp.sendRedirect("home.jsp?bdayFrom=1498-08-07&bdayTo=2017-01-01&withCity=Chicago&withCountry=United+States&withRole=user&isQuery=false");
            }

        } else {
            req.setAttribute("status","failed");
            resp.sendRedirect("index.jsp");
        }

    }
}
