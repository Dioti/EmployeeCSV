package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.model.Employee;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeDAO {

    private static String PROP_FILE = "/db.properties";

    /**
     * Constructor for EmployeeDAO class
     * Initialises data source using database.properties file
     */
    public EmployeeDAO() {
        InputStream stream = EmployeeDAO.class.getResourceAsStream(PROP_FILE);
        SimpleDataSource.init(stream);
    }

    /**
     * Populate Employees table with valid employees
     */
    public void populateEmployees(ArrayList<Employee> validEmployees) {
        for(Employee e : validEmployees) {
            addEmployee(e);
        }
    }

    /**
     * Create Employees table
     */
    public void createEmployeesTable() {
        try (Connection con = SimpleDataSource.getConnection();
             Statement st = con.createStatement()){
            st.executeUpdate("DROP TABLE IF EXISTS Employee");
            st.executeUpdate("CREATE TABLE `project`.`employee` (\n" +
                    "  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                    "  `title` VARCHAR(6) NULL,\n" +
                    "  `forename` VARCHAR(45) NOT NULL,\n" +
                    "  `middlename` VARCHAR(45) NULL,\n" +
                    "  `surname` VARCHAR(45) NOT NULL,\n" +
                    "  `gender` CHAR(1) NOT NULL,\n" +
                    "  `email` VARCHAR(45) NULL,\n" +
                    "  `birth_date` DATE NOT NULL,\n" +
                    "  `join_date` DATE NOT NULL,\n" +
                    "  `salary` INT NOT NULL DEFAULT 0,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add an employee to Employees table
     */
    public void addEmployee(Employee e) {
        String sql = "INSERT INTO Employee (id, title, forename, middlename, surname, gender, email, birth_date, join_date, salary) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection con = SimpleDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, e.getId());
            pst.setString(2, e.getTitle());
            pst.setString(3, e.getForename());
            pst.setString(4, e.getMiddlename());
            pst.setString(5, e.getSurname());
            pst.setString(6, String.valueOf(e.getGender()));
            pst.setString(7, e.getEmail());
            pst.setDate(8, e.getDob());
            pst.setDate(9, e.getJoinDate());
            pst.setInt(10, e.getSalary());
            pst.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}


