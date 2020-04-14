package com.thoughtworks.scc.sccdemo;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private int id;
    private String name;
    private int age;
    private String gender;
    private int salary;

    public static List<Employee> createTestEmployees() {
        List<Employee> employees=new ArrayList<>();
        employees.add(new Employee(1,"xiaoming",22,"male",5000));
        employees.add(new Employee(2,"xiaohong",24,"female",6000));
        return employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Employee() {
    }

    public Employee(int id, String name, int age, String gender, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }
}