package com.sparta.employeecsv.model;

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

    /**
     * Default constructor for an instance of the Employee class
     */
    public Employee() {
        id = -1;
        title = null;
        forename = null;
        middlename = null;
        surname = null;
        gender = '\0';
        email = null;
        dob = null;
        joinDate = null;
        salary = 0;
    }

    /**
     * Constructor for an instance of the Employee class
     * @param id
     * @param title
     * @param forename
     * @param middlename
     * @param surname
     * @param gender
     * @param email
     * @param dob
     * @param joinDate
     * @param salary
     */
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

    /**
     * Checks if the employee is valid/contains default values
     * @return whether the employee is valid or not
     */
    public boolean isValid() {
        if (id == -1 || title == null || forename == null || surname == null || gender == '\0' || dob == null || joinDate == null || salary < 0 ){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Getter for employee id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for employee id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for employee title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for employee title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for employee forename
     * @return forename
     */
    public String getForename() {
        return forename;
    }

    /**
     * Setter for employee forename
     * @param forename
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * Getter for employee middlename
     * @return middlename
     */
    public String getMiddlename() {
        return middlename;
    }

    /**
     * Setter for employe middlename
     * @param middlename
     */
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    /**
     * Getter for employee surname
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter for employee surname
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Getter for employee gender
     * @return gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * Setter for employee gender
     * @param gender
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * Getter for employee email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for employee email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for employee date of birth
     * @return dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Setter for employee date of birth
     * @param dob
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * Getter for employee join date
     * @return joinDate
     */
    public Date getJoinDate() {
        return joinDate;
    }

    /**
     * Setter for employee join date
     * @param joinDate
     */
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * Getter for employee salary
     * @return salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Setter for employee salary
     * @param salary
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Overriden toString() method for an instance of the Employee class
     * @return the string that represents the employee
     */
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", forename='" + forename + '\'' +
                ", middlename='" + middlename + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", joinDate=" + joinDate +
                ", salary=" + salary +
                '}';
    }
}
