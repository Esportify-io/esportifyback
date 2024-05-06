package com.marco.Esportify.controller;

import com.marco.Esportify.domain.Organization;
import com.marco.Esportify.model.TeamCreateRequest;
import com.marco.Esportify.model.TeamCreateResponse;
import com.marco.Esportify.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/team")
@AllArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/create")
    public ResponseEntity<TeamCreateResponse> create(@RequestBody TeamCreateRequest teamCreateRequest, Authentication authentication) {
        Jwt jwtToken = (Jwt) authentication.getPrincipal();
        String id = jwtToken.getSubject();
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.create(teamCreateRequest, id));
    }
}
