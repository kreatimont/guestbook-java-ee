package main.java.io.kreatimont.task.utils;

import java.io.*;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseManager {

    private static DatabaseManager databaseManager = new DatabaseManager();

    private DatabaseManager() {
    }

    public static DatabaseManager instance() {
        return databaseManager;
    }

    private String dbClass = "com.mysql.cj.jdbc.Driver";
    private String dbUrl = "jdbc:mysql://localhost:3306/taskdb&useSSL=true&serverTimezone=UTC";
    /*"jdbc:mysql://localhost:3306/taskdb?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                            "&useLegacyDatetimeCode=false&serverTimezone=UTC";*/

    //test root - < user
    private String dbUsername = "root";
    private String dbPassword = "1mysqlPass";

    public static final String USERS_TABLE = "users";

    public static List<String> queries = new ArrayList<>();

    public Connection getConnection() {
        try {
            Class.forName(dbClass);
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
