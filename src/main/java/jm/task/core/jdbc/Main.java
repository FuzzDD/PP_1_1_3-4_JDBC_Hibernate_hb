package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl jdbc = new UserServiceImpl();
        jdbc.createUsersTable();
        jdbc.saveUser("Ivan", "Ivanov", (byte) 18);
        jdbc.saveUser("Petr", "Petrov", (byte) 19);
        jdbc.saveUser("Sidr", "Sidorov", (byte) 20);
        jdbc.saveUser("James", "Bond", (byte) 21);
        System.out.println(jdbc.getAllUsers());
        jdbc.cleanUsersTable();
        jdbc.dropUsersTable();
    }
}
