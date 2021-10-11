package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.model.Employee;

import java.util.ArrayList;

public class ThreadedWriter implements Runnable { // can use extends but class can only have one superclass

    private int threadID;
    private ArrayList<Employee> list;
    private EmployeeDAO dao;

    /**
     * Constructor for an instance of the ThreadedWriter class
     * @param threadID : the id of the thread
     * @param list : the list of employees to be written to the database
     * @param dao : the employee data access object associated with the instance
     */
    public ThreadedWriter(int threadID, ArrayList<Employee> list, EmployeeDAO dao) {
        this.threadID = threadID;
        this.list = list;
        this.dao = dao;
    }

    /**
     * Getter for the thread id
     * @return threadID
     */
    public int getThreadID() {
        return threadID;
    }

    /**
     * Setter for the thread id
     * @param threadID
     */
    public void setThreadID(int threadID) {
        this.threadID = threadID;
    }

    /**
     * Getter for the list of employees
     * @return list
     */
    public ArrayList<Employee> getList() {
        return list;
    }

    /**
     * Setter for the list of employees
     * @param list
     */
    public void setList(ArrayList<Employee> list) {
        this.list = list;
    }

    /**
     * Getter for the employee data access object
     * @return dao
     */
    public EmployeeDAO getDao() {
        return dao;
    }

    /**
     * Setter for the employee data access object
     * @param dao
     */
    public void setDao(EmployeeDAO dao) {
        this.dao = dao;
    }

    /**
     * Populates the database with the list of employees
     */
    public void populate() {
        System.out.println("Thread-" + threadID + " running...");
        dao.populateBatchEmployees(list);
        System.out.println("Finishing up Thread-" + threadID);
    }

    /**
     * Runs the thread
     */
    @Override
    public void run() {
        populate();
    }
}
