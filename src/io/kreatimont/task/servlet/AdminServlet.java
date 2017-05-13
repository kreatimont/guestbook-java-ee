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

        String bdayFrom = req.getParameter("bdayFrom");
        String bdayTo = req.getParameter("bdayTo");
        String withCity = req.getParameter("withCity");
        String withCountry = req.getParameter("withCountry");
        String withRole = req.getParameter("withRole");

        req.setAttribute("bdayFrom", bdayFrom);
        req.setAttribute("bdayTo", bdayTo);
        req.setAttribute("withCity", withCity);
        req.setAttribute("withCountry", withCountry);
        req.setAttribute("withRole", withRole);

        req.getSession().setAttribute("isQuery",true);

        req.setAttribute("queryUser", Validator.getUsersWith(bdayFrom,bdayTo,withCountry,withCity,withRole));

        resp.sendRedirect("admin.jsp");
        resp.setHeader("REFRESH", "0");

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
