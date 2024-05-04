package com.marco.Esportify.service;

import com.marco.Esportify.domain.Organization;
import com.marco.Esportify.domain.User;
import com.marco.Esportify.model.OrganizationCreateRequest;
import com.marco.Esportify.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final UserService userService;

    public Organization create(OrganizationCreateRequest organizationCreateRequest, String id) {
        Optional<Organization> optionalOrganization = organizationRepository.findByName(organizationCreateRequest.getName());
        if (optionalOrganization.isPresent()) throw new RuntimeException("Organization already exists");

        Organization organization = Organization.builder()
                .name(organizationCreateRequest.getName())
                .build();

        Organization organizationSaved = organizationRepository.save(organization);

        userService.setOrganization(id, organizationSaved);

        return organization;
    }
}