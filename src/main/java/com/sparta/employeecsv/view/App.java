package com.sparta.employeecsv.view;

import com.sparta.employeecsv.controller.CSVReader;
import com.sparta.employeecsv.controller.EmployeeDAO;
import com.sparta.employeecsv.model.Employee;

public class App {

    private long start;
    private long stop;

    public static void main(String[] args) {
        // read csv
        CSVReader reader = new CSVReader();
        long start = System.currentTimeMillis();
        reader.read("src/main/resources/EmployeeRecords.csv");
        long stop = System.currentTimeMillis();
        System.out.println(reader.getStats());
        System.out.println("Time taken to populate Java collections: " + (stop - start) + " ms (~" + Math.round((stop - start) / 1000) + " seconds)");

        // populate database table
        EmployeeDAO dao = new EmployeeDAO();
        dao.createEmployeesTable();
        start = System.currentTimeMillis();
        dao.populateEmployees(reader.getValidEmployees());
        stop = System.currentTimeMillis();
        System.out.println("Time taken to populate database: " + (stop - start) + " ms (~" + Math.round((stop - start) / 1000) + " seconds)");
    }
}
