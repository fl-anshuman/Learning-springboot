package org.CRUD.Learning;

import java.util.List;

public interface EmployeeService {
    public String createEmployee(Employee employee);
    public Employee getEmployeeById(int id);
    public Employee updateEmployee(int id, Employee employee);
    public boolean deleteEmployee(int id);
    public List<Employee> getAllEmployees();

}
