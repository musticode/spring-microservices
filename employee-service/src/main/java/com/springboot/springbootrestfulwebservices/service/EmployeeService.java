package com.springboot.springbootrestfulwebservices.service;

import com.springboot.springbootrestfulwebservices.payload.APIResponseDto;
import com.springboot.springbootrestfulwebservices.payload.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long id);
    List<EmployeeDto> getEmployees();

    List<APIResponseDto> getEmployeesAndDepartments();

    APIResponseDto getEmployeeWithDepartmentAndOrganization(Long id);
}
