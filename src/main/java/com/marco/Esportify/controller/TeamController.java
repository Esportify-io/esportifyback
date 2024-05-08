package com.marco.Esportify.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marco.Esportify.domain.Team;
import com.marco.Esportify.model.TeamCreateRequest;
import com.marco.Esportify.model.TeamCreateResponse;
import com.marco.Esportify.model.TeamGetResponse;
import com.marco.Esportify.model.TeamPlayersResponse;
import com.marco.Esportify.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/team")
@AllArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/create")
    @JsonIgnore
    public ResponseEntity<TeamCreateResponse> create(@RequestBody TeamCreateRequest teamCreateRequest, Authentication authentication) {
        Jwt jwtToken = (Jwt) authentication.getPrincipal();
        String id = jwtToken.getSubject();
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.create(teamCreateRequest, id));
    }

    @GetMapping("{id}")
    @JsonIgnore
    public ResponseEntity<TeamGetResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(teamService.getById(id));
    }

    @GetMapping("{id}/players")
    public ResponseEntity<TeamPlayersResponse> getPlayersById(@PathVariable Long id) {
        return ResponseEntity.ok().body(teamService.getPlayersById(id));
    }
}
