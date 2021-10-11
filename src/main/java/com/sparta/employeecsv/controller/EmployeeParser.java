package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.model.Employee;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class EmployeeParser {

    private static final Set<String> TITLE = new HashSet<>(Arrays.asList(
            "Mr.", "Miss.", "Mrs.", "Mx.", "Prof.", "Hon.", "Dr.", "Drs.")); // add more or whatever
    private static final Set<Character> GENDER = new HashSet<>(Arrays.asList(
            'M', 'F', 'N'));

    /**
     * Parses the employee string and returns an instance of the Employee class
     * @param row : the string data that represents the employee to be added
     * @return the newly created instance of the Employee to be added
     */
    public static Employee getAsEmployee(String row) {
        String[] values = row.split(",");
        return new Employee(parseID(values[0]),
                parseTitle(values[1]),
                values[2],
                values[3],
                values[4],
                parseGender(values[5]),
                values[6],
                parseDate(values[7]),
                parseDate(values[8]),
                parseSalary(values[9]));
    }

    /**
     * Checks whether the employee to be added is valid using regex
     * @param row : the string data that represents the employee to be added
     * @return whether the data is valid or not
     */
    public static boolean validator(String row) {
        String[] values = row.split(",");
        if (!values[1].matches("[a-zA-Z]+\\.") // invalid title
                || !values[2].matches("\\p{L}+") // invalid forename
                || !values[3].substring(0,1).matches("[a-zA-Z]+") // invalid middle name (initial)
                || !values[4].matches("\\p{L}+") // invalid surname
                || !values[5].matches("[FMfmNn]") // invalid gender
                || !values[6].matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])") // invalid email
                || !values[7].matches("(0?[1-9]|1[0-2])[\\/?$](0?[1-9]|[12]\\d|30|31)[\\/?$](\\d{4}|\\d{2})") // invalid dob
                || !values[8].matches("(0?[1-9]|1[0-2])[\\/?$](0?[1-9]|[12]\\d|30|31)[\\/?$](\\d{4}|\\d{2})")) { // invalid join date
            return false;
        } else {
            return true;
        }
    }

    /**
     * Parses the employee ID
     * @param input : the id as a String
     * @return the id as an int
     */
    public static int parseID(String input) {
        int id = -1;
        try {
            id = Integer.parseInt(input);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return id;
        }
    }

    /**
     * Parses the employee title
     * @param input : the title as a String
     * @return the title as a string
     */
    public static String parseTitle(String input) {
        String title = null;
        try {
            if (!TITLE.add(input)) {
                title = input;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return title;
        }
    }

    /**
     * Parses the employee gender
     * @param input : the gender as a String
     * @return the gender as a char
     */
    public static char parseGender(String input) {
        char g = '\0';
        try {
            char c = input.charAt(0);
            if (!GENDER.add(c)) {
                g = c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return g;
        }
    }

    /**
     * Parses the employee dates
     * @param input : the date as a String
     * @return the date as a java.sql.Date
     */
    public static Date parseDate(String input) {
        Date d = null;
        try {
            String[] values = input.split("/");
            d = Date.valueOf(String.format("%d-%02d-%02d", // return as YYYY-MM-DD
                    Integer.parseInt(values[2]),
                    Integer.parseInt(values[0]),
                    Integer.parseInt(values[1])));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return d;
        }
    }

    /**
     * Parses the employee salary
     * @param input : the salary as a String
     * @return the salary as an int
     */
    public static int parseSalary(String input) {
        int salary = 0;
        try {
            salary = Integer.parseInt(input);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return salary;
        }
    }

}
