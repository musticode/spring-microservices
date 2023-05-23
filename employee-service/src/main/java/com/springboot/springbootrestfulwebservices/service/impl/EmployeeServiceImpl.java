package com.springboot.springbootrestfulwebservices.service.impl;

import com.springboot.springbootrestfulwebservices.configuration.APIClient;
import com.springboot.springbootrestfulwebservices.configuration.OrganizationAPIClient;
import com.springboot.springbootrestfulwebservices.entity.Employee;
import com.springboot.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.springboot.springbootrestfulwebservices.payload.APIResponseDto;
import com.springboot.springbootrestfulwebservices.payload.DepartmentDto;
import com.springboot.springbootrestfulwebservices.payload.EmployeeDto;
import com.springboot.springbootrestfulwebservices.payload.OrganizationDto;
import com.springboot.springbootrestfulwebservices.repository.EmployeeRepository;
import com.springboot.springbootrestfulwebservices.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.management.StringValueExp;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
//    private RestTemplate restTemplate;
    private String DEPARTMENTS_ENDPOINT = "http://localhost:8080/api/departments/";
    private String ORGANIZATION_ENDPOINT = "http://localhost:8083/api/organizations/";

    private WebClient webClient;

    private APIClient apiClient;
    private OrganizationAPIClient organizationAPIClient;



    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               ModelMapper modelMapper,
                               //RestTemplate restTemplate,
                               WebClient webClient,
                               APIClient apiClient,
                               OrganizationAPIClient organizationAPIClient) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        //this.restTemplate = restTemplate;
        this.webClient = webClient;
        this.apiClient = apiClient;
        this.organizationAPIClient = organizationAPIClient;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        //department adding
        employee.setDepartmentCode(employeeDto.getDepartmentCode());

        Employee savedEmployee = employeeRepository.save(employee);

        return mapToDto(savedEmployee);
    }





    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    //@Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long id) { //1



        LOGGER.info("inside getEmployeeByID() method");
        Employee employee = employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee", "id", String.valueOf(id))
        );

        // restTemplate implementation

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
//                DEPARTMENTS_ENDPOINT.concat(employee.getDepartmentCode()),
//                DepartmentDto.classp
//        );


        // webclient implementation
//        DepartmentDto departmentDto = webClient
//                .get()
//                .uri(DEPARTMENTS_ENDPOINT.concat(employee.getDepartmentCode()))
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();


        // Open feign Api client
        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());


//        DepartmentDto departmentDto = responseEntity.getBody();

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(mapToDto(employee));
        apiResponseDto.setDepartmentDto(departmentDto);


        return apiResponseDto;
    }

    // web client GET organization
    public OrganizationDto getOrganizationDto(String depCode){
         //webclient implementation
//        String depCode = "TEST_CODE3";

        OrganizationDto organizationDto = webClient
                .get()
                //.uri(ORGANIZATION_ENDPOINT.concat(employee.getDepartmentCode()))
                .uri(ORGANIZATION_ENDPOINT.concat(depCode))
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        return organizationDto;
    }


    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception){

        LOGGER.info("inside getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(employeeId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and dev dep");

        EmployeeDto employeeDto = mapToDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;

    }


    @Override
    public List<EmployeeDto> getEmployees() {

        List<Employee> employeesList = employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        for (Employee employee : employeesList){
            employeeDtoList.add(mapToDto(employee));
        }

        return employeeDtoList;
    }

    @Override
    public List<APIResponseDto> getEmployeesAndDepartments() {

        List<Employee> employeesList = employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();


//        for (Employee employee : employeesList){
//            ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
//                        DEPARTMENTS_ENDPOINT.concat(employee.getDepartmentCode()),
//                DepartmentDto.class
//            );
//        }

//
//        DepartmentDto departmentDto = responseEntity.getBody();
//
//        APIResponseDto apiResponseDto = new APIResponseDto();
//        apiResponseDto.setEmployeeDto(mapToDto(employee));
//        apiResponseDto.setDepartmentDto(departmentDto);
//


        return null;
    }




    @Override
    public APIResponseDto getEmployeeWithDepartmentAndOrganization(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee", "id", String.valueOf(id)));

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        /**
         * open feign
         *
        OrganizationDto organizationDto = organizationAPIClient.getOrganizationByCode(employee.getOrganizationCode());
        LOGGER.info("organization code open feign: "+ employee.getOrganizationCode());
         *
         */

        OrganizationDto organizationDto = organizationAPIClient.getOrganizationByCode(employee.getOrganizationCode());
        System.out.println("org code: "+organizationDto.getOrganizationCode());

        /**
         * webclient implementation
        //OrganizationDto organizationDto = getOrganizationDto(employee.getOrganizationCode());
        *
         * */
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(mapToDto(employee));
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setOrganizationDto(organizationDto);


        return apiResponseDto;

    }


    private EmployeeDto mapToDto(Employee employee){
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

    private Employee mapToEntity(EmployeeDto employeeDto){
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        return employee;
    }
}
