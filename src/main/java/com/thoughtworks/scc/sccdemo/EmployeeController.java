package com.thoughtworks.scc.sccdemo;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.thoughtworks.scc.sccdemo.EmployeeFactory.createTestEmployees;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    @GetMapping
    public List<Employee> getAllEmployees() {
        return createTestEmployees();
    }

    @GetMapping("/{id}")
    public Employee getSpecEmployee(@PathVariable int id) {
        return createTestEmployees().stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @GetMapping(value = "", params = {"page", "pageSize"})
    public List<Employee> getPageEmployees(@RequestParam int page, @RequestParam int pageSize) {
        return createTestEmployees().stream()
                .skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "", params = {"gender"})
    public List<Employee> getSpeSalarycEmployee(@RequestParam String gender) {
        return createTestEmployees().stream()
                .filter(it -> it.getGender().equals(gender))
                .collect(Collectors.toList());
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
        return employees.stream().map(originEmployee -> {
            if (originEmployee.getId() == employee.getId()) {
                originEmployee = employee;
            }
            return originEmployee;
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public List<Employee> deleteEmployee(@PathVariable int id) {
        return createTestEmployees().stream()
                .filter(employee -> employee.getId() != id)
                .collect(Collectors.toList());
    }
}