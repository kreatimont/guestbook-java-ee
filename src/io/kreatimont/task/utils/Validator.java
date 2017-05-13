package io.kreatimont.task.utils;

import io.kreatimont.task.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Validator {

    public static final String DB_CLASS = "com.mysql.cj.jdbc.Driver";

    public static final String DB_URL = "jdbc:mysql://localhost:3306/taskdb";
    public final static String URLFIXED =
            "jdbc:mysql://localhost:3306/taskdb?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                    "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "0451";
    public static final String USERS_TABLE = "users";

    public static List<String> queries = new ArrayList<>();

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
                user.setRole(resultSet.getString("role"));
                return user;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<User> getAllUsers() {
        try {

            Class.forName(DB_CLASS);

            Connection connection = DriverManager.getConnection(URLFIXED,DB_USERNAME,DB_PASSWORD);
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT * FROM " + USERS_TABLE + ";");


            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"));
                user.setCity(resultSet.getString("city"));
                user.setCountry(resultSet.getString("country"));
                user.setPhone(resultSet.getString("phone_number"));
                user.setBday(resultSet.getDate("bday"));
                user.setRole(resultSet.getString("role"));

                users.add(user);
            }
            return users;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<User> getUsersWith(String bdayFrom, String bdayTo,
                                          String withCountry, String withCity,
                                          String withRole) {

        Validator.queries.add("Start getUserWith");

        try {

            Class.forName(DB_CLASS);

            Connection connection = DriverManager.getConnection(URLFIXED,DB_USERNAME,DB_PASSWORD);

            Validator.queries.add("get connection");

            String query = "select * from " + USERS_TABLE + " " +
                    "where bday between '" + bdayFrom + "' and '" + bdayTo + "' ";

            Validator.queries.add("query: " + query);

            if(withCountry.length() > 0) {
                query += (" and country = '" + withCountry + "' ");
            }

            if(withCity.length() > 0) {
                query += (" and city = '" + withCity + "' ");
            }

            if(withRole.length() > 0) {
                query += (" and role = '" + withRole + "' ");
            }

            query += (";");

            Validator.queries.add("result query: " + query);

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"));
                user.setCity(resultSet.getString("city"));
                user.setCountry(resultSet.getString("country"));
                user.setPhone(resultSet.getString("phone_number"));
                user.setBday(resultSet.getDate("bday"));
                user.setRole(resultSet.getString("role"));

                users.add(user);
            }
            return users;
        } catch (Exception ex) {
            System.out.println(queries.get(Validator.queries.size() - 1));
            ex.printStackTrace();
        }
        return null;
    }


}
