package com.kainos.team0;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
    private static Connection c;

    public static Connection getConnection() {
        if (c == null) {
            String user;
            String password;
            String host;
            try {
                Properties props = new Properties();
                props.load(new FileInputStream("db.properties"));

                user = props.getProperty("user");
                password = props.getProperty("password");
                host = props.getProperty("host");

                if (user == null || password == null || host == null)
                    throw new IllegalArgumentException(
                            "db.properties file must exist and must contain user, password, and host properties.");

                c = DriverManager.getConnection("jdbc:mysql://" + host + "/Group1", user, password);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return c;
    }

    public static boolean testConnection() {
        c = getConnection();
        try {
            Statement s = c.createStatement();
            s.executeQuery("SELECT 1");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
