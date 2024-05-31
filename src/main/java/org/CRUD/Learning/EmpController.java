package org.CRUD.Learning;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@RestController
//public class EmpController {
//    List<Employee>employees=new ArrayList<>();
//
//    @GetMapping("employee")
//    public List<Employee> getAllEmployee() {
//        return employees;
//    }
//
//    @PostMapping("employee")
//    public String createEmployee(@RequestBody Employee employee)
//    {
//        employees.add(employee);
//        return "created employee and saved!!!";
//    }
//
//}
@RestController
public class EmpController {
    EmployeeService employeeService = new EmployeeServiceImpl();
    @GetMapping("employee")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployees();
    }
    @PostMapping("employee")
    public String createEmployee(@RequestBody Employee employee)
    {
        return employeeService.createEmployee(employee);
//        return "created employee and saved!!!";
    }
    @GetMapping("employee/{id}")
    public Employee getEmployeeById(@PathVariable int id)
    {
        return employeeService.getEmployeeById(id);
    }
    @PutMapping("employee/{id}")
    public Employee updateEmployee(@PathVariable int id,@RequestBody Employee employee)
    {
        return employeeService.updateEmployee(id,employee);
    }
    @DeleteMapping("employee/{id}")
    public String deleteEmployee(@PathVariable int id)
    {
          if(employeeService.deleteEmployee(id))
          {
              return "deleted successfully!!!";
          }
          else
          {
              return "Employee not found!!!";
          }
    }


}



