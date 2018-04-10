package main.java.io.kreatimont.task.db;

import com.sun.istack.internal.NotNull;
import main.java.io.kreatimont.task.model.User;
import main.java.io.kreatimont.task.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static UserRepository userRepository;

    private UserRepository() {
    }

    public static UserRepository instance() {
        if (userRepository == null) {
            userRepository = new UserRepository();
            userRepository.connection = DatabaseManager.instance().getConnection();
        }
        return userRepository;
    }

    //DBConnection

    private Connection connection;

    //CRUD operation

    public void addUser(@NotNull User user) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM " + DatabaseManager.USERS_TABLE + " WHERE email = ?");

            preparedStatement.setString(1, user.getEmail());
            ResultSet userWithSameEmail = preparedStatement.executeQuery();
            if (!userWithSameEmail.next()) {
                PreparedStatement addUserStatement = connection.prepareStatement(
                        "INSERT INTO  users (name, surname, email, phone_number, city, country, password, bday, role) VALUES (?,?,?,?,?,?,?,?,?)");
                addUserStatement.setString(1, user.getName());
                addUserStatement.setString(2, user.getSurname());
                addUserStatement.setString(3, user.getEmail());
                addUserStatement.setString(4, user.getPhone());
                addUserStatement.setString(5, user.getCity());
                addUserStatement.setString(6, user.getCountry());

                addUserStatement.executeUpdate();
            }
            //user exist

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User findUserById(@NotNull Integer id) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = this.connection.prepareStatement(
                    "SELECT * FROM " + DatabaseManager.USERS_TABLE + " WHERE ID = ?;");
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = createUserFromResultSet(resultSet);
            if (user != null) return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(@NotNull User user, @NotNull Integer id) {
        System.out.println("[UserRepository updateUser: " + id + "]");
        PreparedStatement preparedStatement;
        ArrayList<User> users = new ArrayList<>();
        try {
            preparedStatement = this.connection.prepareStatement(
                    "UPDATE " + DatabaseManager.USERS_TABLE + " SET name = ?, surname = ?, email = ?, phone_number = ?, city = ?, country = ?, bday = ?, role = ?  WHERE ID = ?;");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getCity());
            preparedStatement.setString(6, user.getCountry());

            preparedStatement.executeUpdate();

            System.out.println("[UserRepository updateUser: " + id + "] successfully updated");

        } catch (SQLException e) {
            System.out.println("[UserRepository updateUser: " + id + "] caught exception + " + e.getLocalizedMessage());
        }
    }

    public void deleteUserById(@NotNull Integer id) {
        System.out.println("[UserRepository deleteUserById: " + id + "]");
        PreparedStatement preparedStatement;
        ArrayList<User> users = new ArrayList<>();
        try {
            preparedStatement = this.connection.prepareStatement(
                    "DELETE FROM " + DatabaseManager.USERS_TABLE + " WHERE id = ?;");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            System.out.println("[UserRepository deleteUserById: " + id + "] successfully deleted");

        } catch (SQLException e) {
            System.out.println("[UserRepository deleteUserById: " + id + "] caught exception + " + e.getLocalizedMessage());
        }
    }

    //custom queries

    public List<User> findAllUsers() {
        PreparedStatement preparedStatement;
        ArrayList<User> users = new ArrayList<>();
        try {
            preparedStatement = this.connection.prepareStatement(
                            "SELECT * FROM " + DatabaseManager.USERS_TABLE + ";");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setCity(resultSet.getString("city"));
                user.setCountry(resultSet.getString("country"));
                user.setPhone(resultSet.getString("phone_number"));
                user.setBday(resultSet.getDate("bday"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User findUserByEmail(String email) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = this.connection.prepareStatement(
                    "SELECT * FROM " + DatabaseManager.USERS_TABLE + " WHERE email = ?;");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = createUserFromResultSet(resultSet);
            if (user != null) return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findUserByEmailAndPassword(String email, String password) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = this.connection.prepareStatement(
                    "SELECT * FROM " + DatabaseManager.USERS_TABLE + " WHERE email = ? AND password = ?;");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = createUserFromResultSet(resultSet);
            if (user != null) return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //additional

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        if(resultSet.next()) {
            User user = new User();
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setEmail(resultSet.getString("email"));
            user.setCity(resultSet.getString("city"));
            user.setCountry(resultSet.getString("country"));
            user.setPhone(resultSet.getString("phone_number"));
            user.setRole(resultSet.getString("role"));
            return user;
        }
        return null;
    }


}
