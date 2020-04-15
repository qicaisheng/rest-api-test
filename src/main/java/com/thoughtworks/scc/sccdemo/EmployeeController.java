package com.thoughtworks.scc.sccdemo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.thoughtworks.scc.sccdemo.Employee.createTestEmployees;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    @GetMapping
    public List<Employee> getAllEmployees() {
        return createTestEmployees();
    }

    @GetMapping("/{id}")
    public Employee getSpecEmployee(@PathVariable int id) {
        List<Employee> employees = createTestEmployees();
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    @GetMapping(value = "", params = {"page", "pageSize"})
    public List<Employee> getPageEmployees(@RequestParam int page, @RequestParam int pageSize) {
        List<Employee> employees = createTestEmployees();
        List<Employee> pageList = new ArrayList<>();
        for (int i = (page - 1) * pageSize; i < page * pageSize; i++) {
            if (i < employees.size()) {
                pageList.add(employees.get(i));
            }
        }
        return pageList;
    }

    @GetMapping(value = "", params = {"gender"})
    public List<Employee> getSpeSalarycEmployee(@RequestParam String gender) {
        return createTestEmployees().stream().filter(it -> it.getGender().equals(gender)).collect(Collectors.toList());
    }

    @PostMapping
    public List<Employee> addEmployees(@RequestBody Employee employee) {
        List<Employee> employees = createTestEmployees();
        employees.add(employee);
        return employees;
    }

    @PutMapping
    public List<Employee> updateEmployee(@RequestBody Employee employee) {
        List<Employee> employees = createTestEmployees();
        for (Employee currentEmployee : employees) {
            if (currentEmployee.getId() == employee.getId()) {
                employees.remove(currentEmployee);
                employees.add(employee);
                break;
            }
        }
        return employees;
    }

    @DeleteMapping("/{id}")
    public List<Employee> deleteEmployee(@PathVariable int id) {
        List<Employee> employees = createTestEmployees();
        for (Employee currentEmployee : employees) {
            if (currentEmployee.getId() == id) {
                employees.remove(currentEmployee);
                break;
            }
        }
        return employees;
    }
}