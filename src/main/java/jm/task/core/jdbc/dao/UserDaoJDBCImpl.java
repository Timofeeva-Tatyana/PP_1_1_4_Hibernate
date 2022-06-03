package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users(" +
                    "id BIGINT NOT NULL AUTO_INCREMENT," +
                    " name CHAR(25) NOT NULL, " +
                    "lastName CHAR(30) NOT NULL," +
                    " age BIT(8) NOT NULL, PRIMARY" +
                    " KEY (id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement line = connection.prepareStatement("INSERT INTO " +
                "Users set name = ?, lastName = ?, age = ?")) {
            line.setString(1, name);
            line.setString(2, lastName);
            line.setByte(3, age);
            line.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (PreparedStatement line = connection.prepareStatement(
                "DELETE FROM Users WHERE Id = ?")) {
            line.setLong(1, id);
            line.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (PreparedStatement line = connection.prepareStatement("SELECT * FROM Users")) {
            ResultSet resultSet = line.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getByte("age"));
                Long id = resultSet.getLong("id");
                user.setId(id);
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;


    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE Users");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
