package com.thoughtworks.scc.sccdemo;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private int id;
    private String companyName;
    private int employeeNumber;
    private List<Employee> employees;
    public Company() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company(int id, String companyName, int employeeNumber, List<Employee> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employeeNumber = employeeNumber;
        this.employees = employees;
    }

    public static List<Company> getTestCompany() {
        List<Employee> employees=new ArrayList<>();
        employees. add(new Employee(1,"小明",10,"male",6000));
        employees. add(new Employee(2,"小红",20,"female",6000));
        Company company=new Company(1,"thoughtworks",employees.size(),employees);
        List<Company> companies=new ArrayList<>();
        companies.add(company);
        companies.add(company);
        companies.add(company);
        companies.add(company);
        companies.add(company);
        companies.add(company);
        companies.add(company);
        return companies;
    }

    public static void getSpecificCompany() {

    }
}