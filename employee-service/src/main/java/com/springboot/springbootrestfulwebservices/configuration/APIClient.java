package com.springboot.springbootrestfulwebservices.configuration;

import com.springboot.springbootrestfulwebservices.payload.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8080/", value = "DEPARTMENT-SERVICE")
@FeignClient(name = "DEPARTMENT-SERVICE") // load balancing
public interface APIClient {

    @GetMapping("api/departments/{departmentCode}")
    DepartmentDto getDepartment(@PathVariable(name = "departmentCode")String departmentCode);




}
