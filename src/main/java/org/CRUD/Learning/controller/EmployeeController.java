package org.CRUD.Learning.controller;


import org.CRUD.Learning.models.Employee;
import org.CRUD.Learning.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired // This annotation is used to inject the object dependency implicitly.
    EmployeeRepository employeeRepository;

    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
        return "data saved successfully";
    }
    @GetMapping("/getEmployee")
    public List<Employee> getEmployee(){
        return employeeRepository.findAll();
    }
    @GetMapping("/getEmployeeById/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        return employeeRepository.findById(id).orElse(null);
    }
    @PutMapping("/updateEmployee")
    public String updateEmployee(@RequestBody Employee employee){
        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElse(null);
        assert existingEmployee != null;
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());
        employeeRepository.save(existingEmployee);
        return "data updated successfully";
    }
    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id){
        employeeRepository.deleteById(id);
        return "data deleted successfully";
    }

}
