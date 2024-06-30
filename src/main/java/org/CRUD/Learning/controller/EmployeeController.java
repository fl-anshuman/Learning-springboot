package org.CRUD.Learning.controller;

import org.CRUD.Learning.dto.CompanyDTO;
import org.CRUD.Learning.dto.EmployeeDTO;
import org.CRUD.Learning.models.Employee;
import org.CRUD.Learning.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return ResponseEntity.ok("Data saved successfully");
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<Employee> employees = employeeService.getEmployee();
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id) {
        Optional<Employee> employee = Optional.ofNullable(employeeService.getEmployeeById(id));
        return employee.map(emp -> ResponseEntity.ok(convertToDTO(emp)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return ResponseEntity.ok("Data updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Data deleted successfully");
    }

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        return ResponseEntity.ok(employeeService.getAllCompanies());
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable int id) {
        CompanyDTO companyDTO = employeeService.getCompanyById(id);
        return companyDTO != null ? ResponseEntity.ok(companyDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping("/companies")
    public ResponseEntity<String> addCompany(@RequestBody CompanyDTO companyDTO) {
        employeeService.addCompany(companyDTO);
        return ResponseEntity.ok("Company added successfully");
    }

    @PutMapping("/companies")
    public ResponseEntity<String> updateCompany(@RequestBody CompanyDTO companyDTO) {
        employeeService.updateCompany(companyDTO);
        return ResponseEntity.ok("Company updated successfully");
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable int id) {
        employeeService.deleteCompany(id);
        return ResponseEntity.ok("Company deleted successfully");
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhone(employee.getPhone());
        return employeeDTO;
    }
}
