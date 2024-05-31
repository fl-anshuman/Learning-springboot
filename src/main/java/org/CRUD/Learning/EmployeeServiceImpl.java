package org.CRUD.Learning;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    List<Employee> employees = new ArrayList<>();
    @Override
    public String createEmployee(Employee employee) {
        employees.add(employee);
        System.out.println(employees);
        return "data saved successfully!!!";
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee data;
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                data = emp;
                return data;
            }
        }
        return null;
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                emp.setName(employee.getName());
                emp.setEmail(employee.getEmail());
                emp.setPhone(employee.getPhone());
                return emp;
            }
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(int id) {
        employees.removeIf(employee -> employee.getId() == id);
        return true;

    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }
}
