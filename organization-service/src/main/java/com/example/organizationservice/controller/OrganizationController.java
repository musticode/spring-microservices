package com.example.organizationservice.controller;

import com.example.organizationservice.payload.OrganizationDto;
import com.example.organizationservice.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto organizationDto1 = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(organizationDto1, HttpStatus.CREATED);
    }

    @GetMapping("/{organizationCode}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable(name = "organizationCode")String code){
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(code);
        return new ResponseEntity<>(organizationDto, HttpStatus.OK);
    }


}
