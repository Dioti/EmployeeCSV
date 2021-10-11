package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.model.Employee;

import java.util.ArrayList;
import java.util.Random;

public class ThreadedWriter implements Runnable { // can use extends but class can only have one superclass

    private int threadID;
    private ArrayList<Employee> list;
    private EmployeeDAO dao;

    public ThreadedWriter(int threadID, ArrayList<Employee> list, EmployeeDAO dao) {
        this.threadID = threadID;
        this.list = list;
        this.dao = dao;
    }

    public int getThreadID() {
        return threadID;
    }

    public void setThreadID(int threadID) {
        this.threadID = threadID;
    }

    public ArrayList<Employee> getList() {
        return list;
    }

    public void setList(ArrayList<Employee> list) {
        this.list = list;
    }

    public EmployeeDAO getDao() {
        return dao;
    }

    public void setDao(EmployeeDAO dao) {
        this.dao = dao;
    }

    public void addEmployee() {
        System.out.println("Thread-" + threadID + " running...");
        synchronized (list) {
            while (!list.isEmpty()) {
                dao.addEmployee(list.get(0));
                //System.out.println(list.get(0));
                list.remove(0);
            }
        }
        System.out.println("Finishing up Thread-" + threadID);
    }

    @Override
    public void run() {
        addEmployee();
    }
}
