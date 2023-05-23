package com.springboot.springbootrestfulwebservices.repository;

import com.springboot.springbootrestfulwebservices.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
