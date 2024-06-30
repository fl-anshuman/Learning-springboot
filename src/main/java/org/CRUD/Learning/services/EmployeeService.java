package org.CRUD.Learning.services;

import org.CRUD.Learning.dto.CompanyDTO;
import org.CRUD.Learning.models.Employee;
import org.CRUD.Learning.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String COMPANY_SERVICE_URL = "http://localhost:8086/companies";

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public void updateEmployee(Employee employee) {
        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElse(null);
        assert existingEmployee != null;
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());
        employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    public List<CompanyDTO> getAllCompanies() {
        return restTemplate.getForObject(COMPANY_SERVICE_URL, List.class);
    }

    public CompanyDTO getCompanyById(int id) {
        return restTemplate.getForObject(COMPANY_SERVICE_URL + "/" + id, CompanyDTO.class);
    }

    public void addCompany(CompanyDTO companyDTO) {
        restTemplate.postForObject(COMPANY_SERVICE_URL, companyDTO, CompanyDTO.class);
    }

    public void updateCompany(CompanyDTO companyDTO) {
        restTemplate.put(COMPANY_SERVICE_URL, companyDTO);
    }

    public void deleteCompany(int id) {
        restTemplate.delete(COMPANY_SERVICE_URL + "/" + id);
    }
}
