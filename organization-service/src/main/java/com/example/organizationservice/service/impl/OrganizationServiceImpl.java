package com.example.organizationservice.service.impl;

import com.example.organizationservice.entity.Organization;
import com.example.organizationservice.payload.OrganizationDto;
import com.example.organizationservice.repository.OrganizationRepository;
import com.example.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private OrganizationRepository organizationRepository;
    private ModelMapper modelMapper;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, ModelMapper modelMapper){
        this.organizationRepository = organizationRepository;
        this.modelMapper = modelMapper;
    }






    private Organization mapToEntity(OrganizationDto organizationDto){
        Organization mappedOrganizationEntity = modelMapper.map(organizationDto, Organization.class);
        return mappedOrganizationEntity;
    }
    private OrganizationDto mapToDto(Organization organization){
        OrganizationDto mappedOrganizationDto = modelMapper.map(organization, OrganizationDto.class);
        return mappedOrganizationDto;
    }

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        Organization organization = new Organization();
//        organization.setId(organizationDto.getId());
        organization.setOrganizationName(organizationDto.getOrganizationName());
        organization.setOrganizationDescription(organizationDto.getOrganizationDescription());
        organization.setOrganizationCode(organizationDto.getOrganizationCode());
        organization.setCreatedDate(organizationDto.getCreatedDate());

        Organization savedOrganization = organizationRepository.save(organization);


        return mapToDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return mapToDto(organization);
    }
}
