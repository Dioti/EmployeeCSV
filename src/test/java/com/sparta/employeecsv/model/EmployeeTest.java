package com.sparta.employeecsv.model;

import com.sparta.employeecsv.model.Employee;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void defaultConstructor() {
        Employee e = new Employee();
        String expected = "Employee{id=-1, " +
                "title='null', " +
                "forename='null', " +
                "middlename='null', " +
                "surname='null', " +
                "gender=\0, " +
                "email='null', " +
                "dob=null, " +
                "joinDate=null, " +
                "salary=0}";
        assertEquals(expected, e.toString());
    }

    @Test
    void getId() {
        Employee e = new Employee();
        assertEquals(-1, e.getId());
    }

    @Test
    void setId() {
        Employee e = new Employee();
        e.setId(123456);
        assertEquals(123456, e.getId());
    }

    @Test
    void getTitle() {
        Employee e = new Employee();
        assertEquals(null, e.getTitle());
    }

    @Test
    void setTitle() {
        Employee e = new Employee();
        e.setTitle("Mrs.");
        assertEquals("Mrs.", e.getTitle());
    }

    @Test
    void getForename() {
        Employee e = new Employee();
        assertEquals(null, e.getForename());
    }

    @Test
    void setForename() {
        Employee e = new Employee();
        e.setForename("Jane");
        assertEquals("Jane", e.getForename());
    }

    @Test
    void getMiddlename() {
        Employee e = new Employee();
        assertEquals(null, e.getMiddlename());
    }

    @Test
    void setMiddlename() {
        Employee e = new Employee();
        e.setMiddlename("M");
        assertEquals("M", e.getMiddlename());
    }

    @Test
    void getSurname() {
        Employee e = new Employee();
        assertEquals(null, e.getSurname());
    }

    @Test
    void setSurname() {
        Employee e = new Employee();
        e.setSurname("Doe");
        assertEquals("Doe", e.getSurname());
    }

    @Test
    void getGender() {
        Employee e = new Employee();
        assertEquals('\0', e.getGender());
    }

    @Test
    void setGender() {
        Employee e = new Employee();
        e.setGender('F');
        assertEquals('F', e.getGender());
    }

    @Test
    void getEmail() {
        Employee e = new Employee();
        assertEquals(null, e.getEmail());
    }

    @Test
    void setEmail() {
        Employee e = new Employee();
        e.setEmail("email@example.com");
        assertEquals("email@example.com", e.getEmail());
    }

    @Test
    void getDob() {
        Employee e = new Employee();
        assertEquals(null, e.getDob());
    }

    @Test
    void setDob() {
        Employee e = new Employee();
        String d = "1998-08-26";
        e.setDob(Date.valueOf(d));
        assertEquals(d, e.getDob().toString());
    }

    @Test
    void getJoinDate() {
        Employee e = new Employee();
        assertEquals(null, e.getJoinDate());
    }

    @Test
    void setJoinDate() {
        Employee e = new Employee();
        String d = "2014-01-15";
        e.setJoinDate(Date.valueOf(d));
        assertEquals(d, e.getJoinDate().toString());
    }

    @Test
    void getSalary() {
        Employee e = new Employee();
        assertEquals(0, e.getSalary());
    }

    @Test
    void setSalary() {
        Employee e = new Employee();
        e.setSalary(9001);
        assertEquals(9001, e.getSalary());
    }

    @Test
    void testToString() {
        Employee e = new Employee(1,
                "Mr.",
                "John",
                "",
                "Smith",
                'M',
                "john.doe@gmail.com",
                Date.valueOf("1998-08-08"),
                Date.valueOf("2021-12-28"),
                1111);
        String expected = "Employee{" +
                "id=1, " +
                "title='Mr.', " +
                "forename='John', " +
                "middlename='', " +
                "surname='Smith', " +
                "gender=M, " +
                "email='john.doe@gmail.com', " +
                "dob=1998-08-08, " +
                "joinDate=2021-12-28, " +
                "salary=1111}";
        assertEquals(expected, e.toString());
    }
}