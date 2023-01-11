package jm.task.core.jdbc.util;

/*import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;*/

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Util {

    /*private String user;
    private String pass;
    private String url;


    public Util(String user, String pass, String url) {
        this.user = user;
        this.pass = pass;
        this.url = url;*/
    //}
    public static Connection getConnect() throws ClassNotFoundException, SQLException {
        String user = "SA";
        String pass = "1234";
        String url = "jdbc:mysql://localhost:3306/mydb";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Connection connection = DriverManager.getConnection(url, user, pass);
        System.out.println("ok");
        return connection;


    }
    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.Driver", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydb")
                .setProperty("hibernate.connection.username", "SA")
                .setProperty("hibernate.connection.password", "1234")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .addAnnotatedClass(User.class);
        return configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build());
    }
}
