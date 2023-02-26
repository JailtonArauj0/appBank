package com.appbank.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {
    private static String port = "5432";
    private static String database = "appbank";
    private static String user = "postgres";
    private static String password = "";
    private static Connection connection = null;

    public static Connection getConnection() {
        return connection;
    }

    static{
        Connect();
    }

    public SingleConnection() {
        Connect();
    }

    public static void Connect() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:" + port + "/" + database;
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
