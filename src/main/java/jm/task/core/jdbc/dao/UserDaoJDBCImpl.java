package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private List<User> usersList = new ArrayList<>();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE `mydbtest`.`users` (\n" +
                "  `user_id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `user_name` VARCHAR(45) NOT NULL,\n" +
                "  `user_lastname` VARCHAR(45) NOT NULL,\n" +
                "  `user_age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`user_id`));";

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(sqlCreate);
        } catch (SQLException e) {

        }
    }

    public void dropUsersTable() {
        String sqlDrop = "DROP TABLE `mydbtest`.`users`;";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(sqlDrop);
        } catch (SQLException e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlSave = "INSERT INTO users(user_name, user_lastname, user_age) VALUES (?, ?, ?)";

        try (Connection connection = Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlSave)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setString(3, String.valueOf(age));

            statement.executeUpdate();
            usersList.add(new User(name, lastName, age));
        } catch (SQLException e) {

        }
    }

    public void removeUserById(long id) {
        String sqlRemove = "DELETE FROM users WHERE id = ? ;";
        try (Connection connection = Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlRemove)) {
            statement.setString(1, String.valueOf(id));
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public List<User> getAllUsers() {

        return usersList;
    }

    public void cleanUsersTable() {
        String sqlClean = "DELETE FROM users;";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(sqlClean);
            usersList.clear();
        } catch (SQLException e) {

        }
    }
}
