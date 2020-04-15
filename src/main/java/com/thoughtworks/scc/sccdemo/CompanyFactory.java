package com.thoughtworks.scc.sccdemo;

import java.util.ArrayList;
import java.util.List;

public class CompanyFactory {
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
}
