package com.sparta.employeecsv;

import java.sql.Date;

public class Employee {
    private int id;
    private String title;
    private String forename;
    private String middlename;
    private String surname;
    private char gender;
    private String email;
    private Date dob;
    private Date joinDate;
    private int salary;

    public Employee() {
        // empty constructor
    }

    public Employee(int id, String title, String forename, String middlename, String surname, char gender, String email, Date dob, Date joinDate, int salary) {
        this.id = id;
        this.title = title;
        this.forename = forename;
        this.middlename = middlename;
        this.surname = surname;
        this.gender = gender;
        this.email = email;
        this.dob = dob;
        this.joinDate = joinDate;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", joinDate=" + joinDate +
                ", salary=" + salary +
                '}';
    }
}
