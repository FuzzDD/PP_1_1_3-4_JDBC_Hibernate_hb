package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (PreparedStatement statement = Util.getConnect()
                .prepareStatement("CREATE TABLE IF NOT EXISTS users (id BIGINT primary key auto_increment, " +
                        "name VARCHAR(10), lastname VARCHAR(10), age TINYINT)")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement statement = Util.getConnect()
                .prepareStatement("DROP TABLE IF EXISTS users")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String insertSQL = "INSERT INTO users (name, lastName,age) VALUES (?, ?, ?)";

        try ( PreparedStatement preparedStatement = Util.getConnect().prepareStatement(insertSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = Util.getConnect()
                .prepareStatement("DELETE FROM users WHERE id = '" + id +"'")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = Util.getConnect()
                .prepareStatement("select * from users")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                user.setId(rs.getLong(1));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (PreparedStatement statement = Util.getConnect()
                .prepareStatement("TRUNCATE TABLE users")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
