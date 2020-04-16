package com.thoughtworks.scc.sccdemo;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.thoughtworks.scc.sccdemo.CompanyFactory.getTestCompany;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @GetMapping
    public List<Company> getAllCompanies() {
        return getTestCompany();
    }

    @GetMapping("/{id}")
    public Company getSpecificCompany(@PathVariable int id) {
        return getTestCompany().stream()
                .filter(company -> company.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getSpecCompanyEmployees(@PathVariable int id) {
        return getTestCompany().stream()
                .filter(company -> company.getId() == id)
                .findFirst()
                .map(Company::getEmployees)
                .orElse(null);
    }

    @GetMapping(value = "", params = {"page", "pageSize"})
    public List<Company> getPageCompanies(@RequestParam int page, @RequestParam int pageSize) {
        return getTestCompany().stream().skip(page * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return company;
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable int id, @RequestBody Company company) {
        return company;
    }

    @DeleteMapping("/{id}")
    public List<Company> deleteCompany(@PathVariable int id) {
        return getTestCompany().stream().filter(company -> company.getId() != id).collect(Collectors.toList());
    }
}