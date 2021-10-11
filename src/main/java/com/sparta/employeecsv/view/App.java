package com.sparta.employeecsv.view;

import com.sparta.employeecsv.controller.CSVReader;
import com.sparta.employeecsv.controller.EmployeeDAO;
import com.sparta.employeecsv.controller.ThreadedWriter;
import com.sparta.employeecsv.model.Employee;

import java.util.ArrayList;
import java.util.stream.Stream;

public class App {

    private static final int NUM_OF_THREADS = 75; // optimal number of threads: 50 - 100

    public static void main(String[] args) {
        // read csv
        CSVReader reader = new CSVReader();
        long start = System.currentTimeMillis();
        reader.read("src/main/resources/EmployeeRecords.csv");
        long stop = System.currentTimeMillis();
        System.out.println(reader.getStats());
        Stream<Employee> invalidEmployeesStream = reader.getInvalidEmployees().stream();
        invalidEmployeesStream.forEach(employee -> System.out.println(employee)); // print invalid entries
        System.out.println("Time taken to populate Java collections: " + (stop - start) + " ms (~" +
                Math.round(Long.valueOf(stop - start).doubleValue() / 1000) + " seconds)");

        // create data access object for Employee
        EmployeeDAO dao = new EmployeeDAO();

        // populate database (single-threaded)
        dao.createEmployeesTable();
        start = System.currentTimeMillis();
        //dao.populateBatchEmployees(reader.getValidEmployees());
        stop = System.currentTimeMillis();
        System.out.println("Time taken to populate database (single-threaded): " + (stop - start) + " ms (~" +
                Math.round(Long.valueOf(stop - start).doubleValue() / 1000) + " seconds)");

        // populate database (multi-threaded)
        ArrayList<Thread> threads = new ArrayList<>();
        ArrayList<Employee> employeeList = reader.getValidEmployees();
        int offset = (int) Math.ceil(employeeList.size() / NUM_OF_THREADS);
        dao.createEmployeesTable(); // remake table
        start = System.currentTimeMillis();
        for(int i = 0; i < NUM_OF_THREADS; i++) {
            int lower = (offset * i);
            int upper = offset + (offset * i);
            if(upper > employeeList.size()) {
                upper = employeeList.size();
            }
            ArrayList<Employee> segment = new ArrayList<>(employeeList.subList(lower, upper));
            Thread t = new Thread(new ThreadedWriter(i + 1, segment, dao));
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
        System.out.println("Time taken to populate database (multi-threaded): " + (stop - start) + " ms (~" +
                Math.round(Long.valueOf(stop - start).doubleValue() / 1000) + " seconds)");
    }
}
