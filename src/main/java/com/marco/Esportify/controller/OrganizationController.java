package com.marco.Esportify.controller;

import com.marco.Esportify.domain.Organization;
import com.marco.Esportify.domain.User;
import com.marco.Esportify.model.OrganizationCreateRequest;
import com.marco.Esportify.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/organization")
@AllArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<Organization> create(@RequestBody OrganizationCreateRequest organizationCreateRequest, Authentication authentication) {
        Jwt jwtToken = (Jwt) authentication.getPrincipal();
        String username = jwtToken.getSubject();
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.create(organizationCreateRequest, username));
    }
}
