package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class CSVReader {

    private ArrayList<Employee> validEmployees;
    private ArrayList<Employee> invalidEmployees;
    private HashSet<Integer> uniqueID;
    private HashSet<Integer> duplicateID;

    public CSVReader () {
        this.validEmployees = new ArrayList<Employee>();
        this.invalidEmployees = new ArrayList<Employee>();
        this.uniqueID = new HashSet<Integer>();
        this.duplicateID = new HashSet<Integer>();
    }

    public ArrayList<Employee> getValidEmployees() {
        return validEmployees;
    }

    public ArrayList<Employee> getInvalidEmployees() {
        return invalidEmployees;
    }

    public HashSet<Integer> getUniqueID() {
        return uniqueID;
    }

    public HashSet<Integer> getDuplicateID() {
        return duplicateID;
    }

    public void read(String fileName) {
        String line = null;
        int currLine = 2;

        // try-with-resources
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            in.readLine(); // pass over first line
            long start = System.currentTimeMillis();
            while ((line = in.readLine()) != null) {
                try {
                    Employee e = EmployeeParser.getAsEmployee(line);
                    int id = e.getId();
                    if(!uniqueID.add(id)) {
                        duplicateID.add(id);
                        invalidEmployees.add(e);
                    } else {
                        uniqueID.add(id);
                        validEmployees.add(e);
                    }
                } catch (Exception e) {
                    System.err.println("Failed to read employee on line " + currLine);
                    //e.printStackTrace();
                }
                currLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStats() {
        return "Total entries: " + (validEmployees.size() + invalidEmployees.size()) + "\n" +
                "Valid entries: " + validEmployees.size() + "\n" +
                "Number of duplicates: " + duplicateID.size();
    }

}
