package com.marco.Esportify.controller;

import com.marco.Esportify.domain.User;
import com.marco.Esportify.model.*;
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
    public ResponseEntity<User> getAuthentication(Authentication authentication) {
        Jwt jwtToken = (Jwt) authentication.getPrincipal();
        String id = jwtToken.getSubject();
        return ResponseEntity.ok().body(userService.getAuthentication(id));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile(Authentication authentication) {
        Jwt jwtToken = (Jwt) authentication.getPrincipal();
        String id = jwtToken.getSubject();
        return ResponseEntity.ok().body(userService.getProfile(id));
    }

    @PatchMapping("/profile")
    public ResponseEntity<UserProfileResponse> patchProfile(@RequestBody UserProfileRequest userProfileRequest, Authentication authentication) {
        Jwt jwtToken = (Jwt) authentication.getPrincipal();
        String id = jwtToken.getSubject();
        return ResponseEntity.ok().body(userService.patchProfile(userProfileRequest, id));
    }
}
