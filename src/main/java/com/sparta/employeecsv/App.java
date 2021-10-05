package com.sparta.employeecsv;

import java.io.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        String line = null;
        // try-with-resources
        try (BufferedReader in = new BufferedReader(new FileReader("src/main/resources/EmployeeRecords.csv"))) {
            in.readLine(); // pass over first line
            while ((line = in.readLine()) != null) {
                employees.add(CSVParser.getAsEmployee(line));
            }
            for (Employee e : employees) {
                System.out.println(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
