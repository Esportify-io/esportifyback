package com.marco.Esportify.controller;

import com.marco.Esportify.model.UserAuthenticationResponse;
import com.marco.Esportify.model.UserRegistrationRequest;
import com.marco.Esportify.model.UserRegistrationResponse;
import com.marco.Esportify.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserRegistrationResponse> create(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userRegistrationRequest));
    }

    @GetMapping("/authentication")
    public ResponseEntity<UserAuthenticationResponse> getAuthentication(Authentication authentication) {
        Jwt jwtToken = (Jwt) authentication.getPrincipal();
        String id = jwtToken.getSubject();
        System.out.println(id);
        return ResponseEntity.ok().body(userService.getAuthentication(id));
    }
}
