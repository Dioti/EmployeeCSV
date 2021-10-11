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

    /**
     * Default constructor for an instance of CSVReader
     */
    public CSVReader () {
        this.validEmployees = new ArrayList<>();
        this.invalidEmployees = new ArrayList<>();
        this.uniqueID = new HashSet<>();
        this.duplicateID = new HashSet<>();
    }

    /**
     * Getter for valid employees
     * @return an arraylist containing valid employees
     */
    public ArrayList<Employee> getValidEmployees() {
        return validEmployees;
    }

    /**
     * Getter for invalid employees
     * @return an arraylist containing invalid employees
     */
    public ArrayList<Employee> getInvalidEmployees() {
        return invalidEmployees;
    }

    /**
     * Getter for unique ids
     * @return a hashset containing unique ids
     */
    public HashSet<Integer> getUniqueID() {
        return uniqueID;
    }

    /**
     * Getter for duplicate ids
     * @return a hashset containing duplicate ids
     */
    public HashSet<Integer> getDuplicateID() {
        return duplicateID;
    }

    /**
     * Reads the csv file line by line
     * Adds valid and invalid employees to their corresponding arraylists
     * Records unique and duplicate employee ids
     * @param fileName : the name of the csv file to be read
     */
    public void read(String fileName) {
        String line;
        int currLine = 2;
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            in.readLine(); // pass over first line
            while ((line = in.readLine()) != null) {
                try {
                    Employee e = EmployeeParser.getAsEmployee(line);
                    int id = e.getId();
                    if(!uniqueID.add(id)) { // if the employee is a duplicate
                        duplicateID.add(id);
                        invalidEmployees.add(e);
                    } else {
                        uniqueID.add(id);
                        if(e.isValid()) {
                            validEmployees.add(e);
                        } else {
                            invalidEmployees.add(e);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Failed to read employee on line " + currLine);
                    e.printStackTrace();
                }
                currLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets statistics about the csv file that has been read
     * @return a string containing the stats
     */
    public String getStats() {
        return "Total entries: " + (validEmployees.size() + invalidEmployees.size()) + "\n" +
                "Number of duplicates: " + duplicateID.size() + "\n" +
                "Valid entries: " + validEmployees.size() + "\n" +
                "Invalid entries: " + invalidEmployees.size();
    }

}
