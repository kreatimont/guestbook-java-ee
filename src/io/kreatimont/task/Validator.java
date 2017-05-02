package io.kreatimont.task;

import io.kreatimont.task.model.User;

import java.sql.*;

public class Validator {

    public static final String DB_CLASS = "com.mysql.cj.jdbc.Driver";

    public static final String DB_URL = "jdbc:mysql://localhost:3306/taskdb";
    public final static String URLFIXED =
            "jdbc:mysql://localhost:3306/taskdb?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                    "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "0451";
    public static final String USERS_TABLE = "users";

    public static User checkUser(String email, String password) {

        try {

            Class.forName(DB_CLASS);

            Connection connection = DriverManager.getConnection(URLFIXED,DB_USERNAME,DB_PASSWORD);
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT * FROM " + USERS_TABLE + " WHERE email=? AND password=?");


            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"));
                user.setCity(resultSet.getString("city"));
                user.setCountry(resultSet.getString("country"));
                user.setPhone(resultSet.getString("phone_number"));
                return user;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
