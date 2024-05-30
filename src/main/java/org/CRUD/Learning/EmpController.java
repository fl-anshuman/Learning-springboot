package org.CRUD.Learning;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmpController {
    List<Employee>employees=new ArrayList<>();

    @GetMapping("employee")
    public List<Employee> getAllEmployee() {
        return employees;
    }

    @PostMapping("employee")
    public String createEmployee(@RequestBody Employee employee)
    {
        employees.add(employee);
        return "created employee and saved!!!";
    }

}
