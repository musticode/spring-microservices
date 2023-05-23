package com.springboot.springbootrestfulwebservices.controller;

import com.springboot.springbootrestfulwebservices.payload.APIResponseDto;
import com.springboot.springbootrestfulwebservices.payload.EmployeeDto;
import com.springboot.springbootrestfulwebservices.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }


    @GetMapping("/{employeeId}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable(name = "employeeId") Long employeeId){
        APIResponseDto employeeDto = employeeService.getEmployeeWithDepartmentAndOrganization(employeeId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees(){
        List<EmployeeDto> employeeDtoList = employeeService.getEmployees();
        return new ResponseEntity<>(employeeDtoList, HttpStatus.OK);
    }

    /**
     * add departmentCode field in employee jpa entity
     * create departmentDto class
     * configure restTemplate as spring bean
     * inject and use restTemplate to make rest api call in employeeServiceImpl class
     */




}
