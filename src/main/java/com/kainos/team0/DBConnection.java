package com.kainos.team0;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class DBConnection {
    private static Connection c;

    public static Connection getConnection() {
        String user;
        String password;
        String host;
        Connection c;
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
            return c;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean testConnection() {
        if (c == null) {
            c = getConnection();
        }
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
