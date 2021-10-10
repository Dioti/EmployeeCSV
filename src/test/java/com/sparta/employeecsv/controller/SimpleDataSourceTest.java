package com.sparta.employeecsv.controller;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class SimpleDataSourceTest {

    @Test
    void getConnection() {
        InputStream stream = SimpleDataSourceTest.class.getResourceAsStream("/db.properties");
        SimpleDataSource.init(stream);
        Connection con = SimpleDataSource.getConnection();
        assertNotNull(con);
    }
}