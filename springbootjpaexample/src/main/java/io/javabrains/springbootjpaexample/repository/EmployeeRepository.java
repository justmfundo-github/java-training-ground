package io.javabrains.springbootjpaexample.repository;

import io.javabrains.springbootjpaexample.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    // By marking the interface as @Repository and extending CrudRepository, we now have access to all CRUD functions
    // provided by JPA Spring
}
