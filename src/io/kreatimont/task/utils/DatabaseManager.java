package io.kreatimont.task.utils;

import com.sun.istack.internal.Nullable;
import io.kreatimont.task.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static DatabaseManager databaseManager = new DatabaseManager();

    private DatabaseManager() {
    }

    public static DatabaseManager instance() {
        return databaseManager;
    }

    public static final String DB_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/taskdb";    /*"jdbc:mysql://localhost:3306/taskdb?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                    "&useLegacyDatetimeCode=false&serverTimezone=UTC";*/

    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "0451";
    public static final String USERS_TABLE = "users";

    public static List<String> queries = new ArrayList<>();

    @Nullable
    public Connection getConnection() {
        try {
            Class.forName(DB_CLASS);
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User getUser(String email, String password) {

        try {
            Class.forName(DB_CLASS);

            Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM " + USERS_TABLE + " WHERE email=? AND password=?");

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

            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
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

    public static List<User> getUsersWith(String bdayFrom, String bdayTo, String withCountry, String withCity, String withRole) {
        try {
            Class.forName(DB_CLASS);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String query = "select * from " + USERS_TABLE + " where bday between '" + bdayFrom + "' and '" + bdayTo + "' ";

            if(withCountry.length() > 0) { query += (" and country = '" + withCountry + "' "); }
            if(withCity.length() > 0) { query += (" and city = '" + withCity + "' "); }
            if(withRole.length() > 0) { query += (" and role = '" + withRole + "' "); }
            query += (";");

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("city"),
                        resultSet.getString("country"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("role"),
                        resultSet.getDate("bday")
                );
                users.add(user);
            }
            DatabaseManager.queries.add("(query): " + query);
            return users;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
