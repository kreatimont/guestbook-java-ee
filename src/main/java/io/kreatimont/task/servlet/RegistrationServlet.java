package main.java.io.kreatimont.task.servlet;

import main.java.io.kreatimont.task.model.User;
import main.java.io.kreatimont.task.db.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String city = req.getParameter("city");
        String country = req.getParameter("country");
        String phone = req.getParameter("phone");
        String bday = req.getParameter("bday");

        User newUser = new User();
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setEmail(email);
        newUser.setCity(city);
        newUser.setCountry(country);
        newUser.setPhone(phone);
        newUser.setBday(new Date());
        newUser.setPassword(password);

        UserRepository.instance().addUser(newUser);

        req.getSession().setAttribute("user", newUser);
        resp.sendRedirect("/home");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/signup.jsp").forward(req, resp);
    }
}
