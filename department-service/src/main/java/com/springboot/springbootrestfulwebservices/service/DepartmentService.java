package com.springboot.springbootrestfulwebservices.service;

import com.springboot.springbootrestfulwebservices.payload.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentByCode(String code);
    List<DepartmentDto> getAllDepartments();
}
