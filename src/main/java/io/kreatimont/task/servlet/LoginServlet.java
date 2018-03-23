package main.java.io.kreatimont.task.servlet;


import main.java.io.kreatimont.task.model.User;
import main.java.io.kreatimont.task.model.UserRepository;
import main.java.io.kreatimont.task.utils.DatabaseManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInput;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = UserRepository.instance().findUserByEmailAndPassword(email, password);

        if(user != null) {

            String homePath = "/home";

            if (user.getRole().equals("admin")) {
                homePath = "/admin";
            }

            req.getSession().setAttribute("user", user);
            resp.sendRedirect(homePath);
        } else {
            req.setAttribute("status","failed");
            this.getServletContext().getRequestDispatcher("index.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatabaseManager.instance().parseConfigurationFromJson();
        this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
