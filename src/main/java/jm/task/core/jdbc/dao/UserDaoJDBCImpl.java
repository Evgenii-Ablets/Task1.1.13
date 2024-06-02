package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age TINYINT);");
            statement.close();
            Util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.execute("DROP TABLE IF EXISTS users;");
            statement.close();
            Util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.execute("INSERT INTO users (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', " + age + ");");
            statement.close();
            Util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.execute("DELETE FROM users WHERE id = " + id + ";");
            statement.close();
            Util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try {
            Statement statement = Util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users;");
            List<User> userList = new java.util.ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
            statement.close();
            Util.closeConnection();
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.execute("DELETE FROM users;");
            statement.close();
            Util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
