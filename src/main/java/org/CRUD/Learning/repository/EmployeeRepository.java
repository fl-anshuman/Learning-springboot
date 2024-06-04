package org.CRUD.Learning.repository;

import org.CRUD.Learning.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository <Employee, Integer>{
}
