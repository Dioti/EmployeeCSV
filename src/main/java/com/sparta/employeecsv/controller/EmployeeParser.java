package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.model.Employee;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class EmployeeParser {

    private static final Set<String> TITLE = new HashSet<String>(Arrays.asList(
            "Mr.", "Miss.", "Mrs.", "Mx.", "Prof.", "Hon.", "Dr.", "Drs.")); // add more or whatever
    private static final Set<Character> GENDER = new HashSet<Character>(Arrays.asList(
            'M', 'F', 'N'));

    public static Employee getAsEmployee(String row) {
        String[] values = row.split(",");
        return new Employee(parseID(values[0]),
                parseTitle(values[1]),
                values[2],
                values[3],
                values[4],
                parseGender(values[5]),
                parseEmail(values[6]),
                parseDate(values[7]),
                parseDate(values[8]),
                parseSalary(values[9]));
    }

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

    public static Character parseGender(String input) {
        Character g = null;
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

    public static String parseEmail(String input) {
        // TODO: email regex woo
        return input;
    }

    public static Date parseDate(String str) {
        Date d = null;
        try {
            String[] values = str.split("/");
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
