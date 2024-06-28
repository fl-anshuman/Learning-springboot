package org.CRUD.Learning.services;

import org.CRUD.Learning.models.Employee;
import org.CRUD.Learning.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public void addEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public List<Employee> getEmployee(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id){
        return employeeRepository.findById(id).orElse(null);
    }

    public void updateEmployee(Employee employee){
        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElse(null);
        assert existingEmployee != null;
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());
        employeeRepository.save(existingEmployee);
    }
    public void deleteEmployee(int id)
    {
        employeeRepository.deleteById(id);
    }



}
