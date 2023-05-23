package com.example.organizationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class OrganizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizationServiceApplication.class, args);
	}

}


/**
 * create org service using spring boot
 * configure mysql database
 * create org jpa entity and spring data jpa repository
 * create organization dto and org mapper
 * build save org rest api
 * build get organization rest api
 * make rest api call from employee-service to organization service
 * register organization-service as eureka client
 * refactor organization service to use config server
 * configure spring cloud bus
 * configure routes for organization-service in api-gateway
 * implement distributed tracing in organization service
* */