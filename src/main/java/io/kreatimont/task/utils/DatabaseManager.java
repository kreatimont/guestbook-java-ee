package main.java.io.kreatimont.task.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.istack.internal.Nullable;

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
    private String dbUrl = "jdbc:mysql://localhost:3306/taskdb";    /*"jdbc:mysql://localhost:3306/taskdb?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                            "&useLegacyDatetimeCode=false&serverTimezone=UTC";*/

    private String dbUsername = "root";
    private String dbPassword = "root";

    public static final String USERS_TABLE = "users";

    public static List<String> queries = new ArrayList<>();

    @Nullable
    public Connection getConnection() {
        this.parseConfigurationFromJson();
        try {
            Class.forName(dbClass);
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void parseConfigurationFromJson() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream(
                "/main/resources/database_properties.json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder out = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            Map<String, String> dataBaseProperties = gson.fromJson(out.toString(), type);

            String dbClassFromJson = dataBaseProperties.get("class");
            String dbUrlFromJson = dataBaseProperties.get("url");
            String dbUsernameFromJson = dataBaseProperties.get("username");
            String dbPasswordFromJson = dataBaseProperties.get("password");

            this.dbClass = dbClassFromJson;
            this.dbUrl = dbUrlFromJson;
            this.dbUsername = dbUsernameFromJson;
            this.dbPassword = dbPasswordFromJson;

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
