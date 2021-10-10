package com.sparta.employeecsv.controller;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 A simple data source for getting database connections.
 */
public class SimpleDataSource
{

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    /**
     Initializes the data source.
     @param stream the input stream read from properties file
     */
    public static void init(InputStream stream)
    {
        Properties props = new Properties();
        try {
            props.load(stream);
        } catch (IOException e) {
            System.err.println("Failed to load properties stream");
        }

        driver = props.getProperty("jdbc.driver");
        url = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        password = props.getProperty("jdbc.password");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load driver class");
        }
    }

    /**
     Gets a connection to the database.
     @return the database connection
     */
    public static Connection getConnection()
    {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("Failed to get database connection");
        }
        return null;
    }
}