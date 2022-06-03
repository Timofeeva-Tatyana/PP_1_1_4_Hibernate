package jm.task.core.jdbc.util;

import java.sql.*;
import java.sql.DriverManager;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public static Connection connection;

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                //System.out.println("Connection successful");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось загрузить класс драйвер");
        }

        return connection;
    }


}
