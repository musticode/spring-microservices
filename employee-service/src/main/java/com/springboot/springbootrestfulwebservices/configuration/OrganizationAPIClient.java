package com.springboot.springbootrestfulwebservices.configuration;

import com.springboot.springbootrestfulwebservices.payload.DepartmentDto;
import com.springboot.springbootrestfulwebservices.payload.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationAPIClient {

    @GetMapping("api/organizations/{organizationCode}")
    OrganizationDto getOrganizationByCode(@PathVariable(name = "organizationCode")String organizationCode);

}
