//package org.CRUD.Learning.controller;
//
//
//import org.CRUD.Learning.models.Employee;
//import org.CRUD.Learning.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class EmployeeController {
//
//    @Autowired // This annotation is used to inject the object dependency implicitly.
//    EmployeeRepository employeeRepository;
//
//    @PostMapping("/addEmployee")
//    public String addEmployee(@RequestBody Employee employee){
//
//        employeeRepository.save(employee);
//        return "data saved successfully";
//    }
//    @GetMapping("/getEmployee")
//    public List<Employee> getEmployee(){
//        System.out.println("requestReceived");
//        return employeeRepository.findAll();
//    }
//    @GetMapping("/getEmployeeById/{id}")
//    public Employee getEmployeeById(@PathVariable int id){
//        return employeeRepository.findById(id).orElse(null);
//    }
//    @PutMapping("/updateEmployee")
//    public String updateEmployee(@RequestBody Employee employee){
//        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElse(null);
//        assert existingEmployee != null;
//        existingEmployee.setName(employee.getName());
//        existingEmployee.setEmail(employee.getEmail());
//        existingEmployee.setPhone(employee.getPhone());
//        employeeRepository.save(existingEmployee);
//        return "data updated successfully";
//    }
//    @DeleteMapping("/deleteEmployee/{id}")
//    public String deleteEmployee(@PathVariable int id){
//        employeeRepository.deleteById(id);
//        return "data deleted successfully";
//    }
//
//}
package org.CRUD.Learning.controller;


import org.CRUD.Learning.models.Employee;
import org.CRUD.Learning.repository.EmployeeRepository;
import org.CRUD.Learning.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired // This annotation is used to inject the object dependency implicitly.
    EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return "data saved successfully";
    }
    @GetMapping("/getEmployee")
    public List<Employee> getEmployee(){

        System.out.println("requestReceived");
        return employeeService.getEmployee();
    }
//    @GetMapping("/getEmployeeById/{id}")
//    public Employee getEmployeeById(@PathVariable int id){
//    return employeeService.getEmployeeById(id);
//    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
        Optional<Employee> employee= Optional.ofNullable(employeeService.getEmployeeById(id));
        if(employee.isPresent()){
            return ResponseEntity.ok(employee.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/updateEmployee")
    public String updateEmployee(@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
        return "data updated successfully";
    }
    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
        return "data deleted successfully";
    }

}
