package io.kreatimont.task;

import io.kreatimont.task.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String city = req.getParameter("city");
        String country = req.getParameter("country");
        String phone = req.getParameter("phone");

        try {

            Class.forName(Validator.DB_CLASS);

            Connection connection = DriverManager.getConnection(Validator.URLFIXED, Validator.DB_USERNAME, Validator.DB_PASSWORD);
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT * FROM " + Validator.USERS_TABLE + " WHERE email=?");

            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {

                PreparedStatement insertUser = connection.prepareStatement
                        ("INSERT INTO users (name, surname, email, phone_number, city, country, password)" +
                                " VALUES ('"+name+"','"+surname+"','"+email+"','"+phone+"','"+city+"','"+country+"','"+ password +"');");

                int i = insertUser.executeUpdate();
                if(i > 0 ) {
                    User user = Validator.checkUser(email, password);
                    req.setAttribute("user", user);
                    req.getSession().setAttribute("user", user);
                    req.getRequestDispatcher("home.jsp").forward(req, resp);
                    return;
                }

            } else {
                req.setAttribute("status","email_exist");
                req.getRequestDispatcher("sign_up.jsp").forward(req,resp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        req.setAttribute("status","ex");
        req.getRequestDispatcher("sign_up.jsp").forward(req,resp);
    }
}
