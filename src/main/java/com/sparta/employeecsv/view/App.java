package com.sparta.employeecsv.view;

import com.sparta.employeecsv.controller.CSVReader;
import com.sparta.employeecsv.controller.EmployeeDAO;
import com.sparta.employeecsv.controller.ThreadedWriter;
import com.sparta.employeecsv.model.Employee;

import java.util.ArrayList;

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

        // create data access object for Employee
        EmployeeDAO dao = new EmployeeDAO();

        // populate database (single-threaded)
        dao.createEmployeesTable();
        start = System.currentTimeMillis();
        dao.populateEmployees(reader.getValidEmployees());
        stop = System.currentTimeMillis();
        System.out.println("Time taken to populate database (single-threaded): " + (stop - start) + " ms (~" + Math.round((stop - start) / 1000) + " seconds)");

        // populate database (multi-threaded)
        int numOfThreads = 3;
        ArrayList<Thread> threads = new ArrayList<>();
        ArrayList<Employee> sharedList = reader.getValidEmployees();
        dao.createEmployeesTable(); // remake table
        start = System.currentTimeMillis();
        for(int i = 1; i < numOfThreads + 1; i++) {
            Thread t = new Thread(new ThreadedWriter(i, sharedList, dao));
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join(); // wait for threads to finish before getting time taken
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stop = System.currentTimeMillis();
        System.out.println("Time taken to populate database (multi-threaded): " + (stop - start) + " ms (~" + Math.round((stop - start) / 1000) + " seconds)");
    }
}
