package com.sparta.employeecsv;

import java.sql.Date;

public class CSVParser {

    public static Employee getAsEmployee(String row) {
        String[] values = row.split(",");
        int id = Integer.parseInt(values[0]);
        String title = values[1];
        String forename = values[2];
        String middlename = values[3];
        String surname = values[4];
        char gender = values[5].charAt(0);
        String email = values[6];
        Date dob = strToDate(values[7]);
        Date joinDate = strToDate(values[8]);
        int salary = Integer.parseInt(values[9]);

        return new Employee(id, title, forename, middlename, surname, gender, email, dob, joinDate, salary);
    }

    private static Date strToDate(String str) {
        String[] values = str.split("/");
        return Date.valueOf(String.format("%d-%02d-%02d",
                Integer.parseInt(values[2]),
                Integer.parseInt(values[0]),
                Integer.parseInt(values[1])));
    }

    private static int sanitiseID(int input) {
        int output = input;
        return output;
    }

    private static String sanitiseTitle(String input) {
        String output = input;
        return output;
    }

    private static String sanitiseName(String input) {
        String output = input;
        return output;
    }

    private static char sanitiseGender(char input) {
        char output = input;
        return output;
    }

    private static String sanitiseEmail(String input) {
        String output = input;
        return output;
    }

    private static Date sanitiseDate(Date input) {
        Date output = input;
        return output;
    }

    private static int sanitiseSalary(int input) {
        int output = input;
        return output;
    }

}
