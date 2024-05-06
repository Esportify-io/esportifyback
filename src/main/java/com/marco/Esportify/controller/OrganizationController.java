package com.marco.Esportify.controller;

import com.marco.Esportify.domain.Organization;
import com.marco.Esportify.domain.User;
import com.marco.Esportify.model.*;
import com.marco.Esportify.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/organization")
@AllArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationCreateResponse> create(@RequestBody OrganizationCreateRequest organizationCreateRequest, Authentication authentication) {
        Jwt jwtToken = (Jwt) authentication.getPrincipal();
        String username = jwtToken.getSubject();
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.create(organizationCreateRequest, username));
    }

    @GetMapping("/members")
    public ResponseEntity<OrganizationMembersResponse> get(Authentication authentication) {
        Jwt jwtToken = (Jwt) authentication.getPrincipal();
        String id = jwtToken.getSubject();
        return ResponseEntity.ok().body(organizationService.get(id));
    }

    @GetMapping("/get/member")
    public ResponseEntity<UserProfileResponse> getOrganizationUserProfile(@RequestParam String email) {
        return ResponseEntity.ok().body(organizationService.getOrganizationUserProfile(email));
    }

    @PatchMapping
    public ResponseEntity<OrganizationCodePatchResponse> patchCode(@RequestBody OrganizationCodePatchRequest organizationCodePatchRequest) {
        return ResponseEntity.ok().body(organizationService.patchCode(organizationCodePatchRequest.getName()));
    }
}
