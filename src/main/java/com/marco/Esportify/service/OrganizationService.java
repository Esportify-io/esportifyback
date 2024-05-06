package com.marco.Esportify.service;

import com.marco.Esportify.domain.Organization;
import com.marco.Esportify.domain.User;
import com.marco.Esportify.model.*;
import com.marco.Esportify.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final UserService userService;

    public OrganizationCreateResponse create(OrganizationCreateRequest organizationCreateRequest, String id) {
        Optional<Organization> optionalOrganization = organizationRepository.findByName(organizationCreateRequest.getName());
        if (optionalOrganization.isPresent()) throw new RuntimeException("Organization already exists");

        Organization organization = Organization.builder()
                .name(organizationCreateRequest.getName())
                .inviteCode(BCrypt.gensalt().substring(23))
                .build();

        User user = userService.getAuthentication(id);
        organization.setMembers(List.of(user));

        Organization organizationSaved = organizationRepository.save(organization);

        userService.setOrganization(id, organizationSaved);

        return OrganizationCreateResponse.builder()
                .organization(organizationSaved)
                .status("CREATED")
                .build();
    }

    public OrganizationMembersResponse get(String id) {
        User user = userService.getAuthentication(id);
        Optional<Organization> organization = organizationRepository.findByName(user.getOrganizations().get(0).getName());
        if (organization.isEmpty()) throw new RuntimeException("Organization not found");

        List<OrganizationMembersRequest> organizationMembersRequest = organization.get().getMembers().stream()
                .map(e -> OrganizationMembersRequest.builder()
                        .name(e.getName())
                        .email(e.getEmail())
                        .phone(e.getPhone())
                        .role(e.getRole().name())
                        .build())
                .toList();

        return OrganizationMembersResponse
                .builder()
                .name(user.getName())
                .id(user.getId())
                .role(user.getRole().name())
                .organizationMembersRequest(organizationMembersRequest)
                .build();
    }

    public UserProfileResponse getOrganizationUserProfile(String email) {
        User user = userService.getUserByEmail(email);

        return UserProfileResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .country(user.getCountry())
                .instagram(user.getInstagram())
                .twitter(user.getTwitter())
                .build();
    }

    public OrganizationCodePatchResponse patchCode(String name) {
        Optional<Organization> optionalOrganization = organizationRepository.findByName(name);
        System.out.println(name);
        if(optionalOrganization.isEmpty()) throw new RuntimeException("Organization not found");

        optionalOrganization.get().setInviteCode(BCrypt.gensalt().substring(23));

        Organization organizationSaved = organizationRepository.save(optionalOrganization.get());

        return OrganizationCodePatchResponse.builder()
                .status("SUCCESS")
                .new_code(organizationSaved.getInviteCode())
                .build();
    }
}