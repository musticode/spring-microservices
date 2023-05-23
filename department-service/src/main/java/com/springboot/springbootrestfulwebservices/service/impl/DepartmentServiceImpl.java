package com.springboot.springbootrestfulwebservices.service.impl;

import com.springboot.springbootrestfulwebservices.entity.Department;
import com.springboot.springbootrestfulwebservices.payload.DepartmentDto;
import com.springboot.springbootrestfulwebservices.repository.DepartmentRepository;
import com.springboot.springbootrestfulwebservices.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                                 ModelMapper modelMapper){
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {



        Department department = new Department();
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        department.setDepartmentCode(departmentDto.getDepartmentCode());

        Department savedDepartment = departmentRepository.save(department);


        return mapToDto(savedDepartment);
    }


    @Override
    public List<DepartmentDto> getAllDepartments(){

        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDto> departmentDtoList = new ArrayList<>();

        for (Department department : departments){
            departmentDtoList.add(mapToDto(department));
        }

        return departmentDtoList;

    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
       Department department = departmentRepository.findByDepartmentCode(code);
       return mapToDto(department);
    }

    private DepartmentDto mapToDto(Department department){
        DepartmentDto departmentDto =  modelMapper.map(department, DepartmentDto.class);
        return departmentDto;
    }

    private Department mapToEntity(DepartmentDto departmentDto){
        Department department =  modelMapper.map(departmentDto, Department.class);
        return department;
    }

}
