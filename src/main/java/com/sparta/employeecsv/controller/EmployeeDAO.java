package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.model.Employee;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeDAO {

    private static final String PROP_FILE = "/db.properties";
    private static final int BATCH_SIZE = 50;

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
     * @param employeeList : the list of employees to be added to the database
     */
    public void populateEmployees(ArrayList<Employee> employeeList) {
        for(Employee e : employeeList) {
            addEmployee(e);
        }
    }

    /**
     * Populate Employees table with valid employees
     * @param employeeList : the list of employees to be added to the database
     */
    public void populateBatchEmployees(ArrayList<Employee> employeeList) {
        String sql = "INSERT INTO Employee (id, title, forename, middlename, surname, gender, email, birth_date, join_date, salary) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection con = SimpleDataSource.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {
            int i = 0;
            for (Employee e : employeeList) {
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
                pst.addBatch();
                i++;
                if (i % BATCH_SIZE == 0 || i == employeeList.size()) { // execute in batches of specified size
                    pst.executeBatch();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Create Employees table
     */
    public void createEmployeesTable() {
        try (Connection con = SimpleDataSource.getConnection();
             Statement st = con.createStatement()){
            st.executeUpdate("DROP TABLE IF EXISTS Employee");
            st.executeUpdate("""
                    CREATE TABLE `project`.`employee` (
                      `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                      `title` VARCHAR(6) NULL,
                      `forename` VARCHAR(45) NOT NULL,
                      `middlename` VARCHAR(45) NULL,
                      `surname` VARCHAR(45) NOT NULL,
                      `gender` CHAR(1) NOT NULL,
                      `email` VARCHAR(45) NULL,
                      `birth_date` DATE NOT NULL,
                      `join_date` DATE NOT NULL,
                      `salary` INT NOT NULL DEFAULT 0,
                      PRIMARY KEY (`id`),
                      UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
                    """);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add an employee to Employees table using a new Connection
     * @param e : the employee to be added
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

    /**
     * Add an employee to a batch using a given Connection
     * @param e : the employee to be added
     * @param con : the database connection
     */
    public void addBatchEmployee(Employee e, Connection con) {
        String sql = "INSERT INTO Employee (id, title, forename, middlename, surname, gender, email, birth_date, join_date, salary) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
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
            pst.addBatch(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}


