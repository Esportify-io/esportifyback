package com.marco.Esportify.service;

import com.marco.Esportify.domain.Organization;
import com.marco.Esportify.domain.Role;
import com.marco.Esportify.domain.User;
import com.marco.Esportify.model.*;
import com.marco.Esportify.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserRegistrationResponse create(UserRegistrationRequest userRegistrationRequest) {

        Optional<User> optionalUser = userRepository.findByEmail(userRegistrationRequest.getEmail());
        if(optionalUser.isPresent()) throw new RuntimeException("User already exists");

        User user = User.builder()
                .name(userRegistrationRequest.getName())
                .email(userRegistrationRequest.getEmail())
                .phone(userRegistrationRequest.getPhone())
                .role(Role.REGISTERED)
                .build();

        System.out.println(userRegistrationRequest);

        user.setPassword(BCrypt.hashpw(userRegistrationRequest.getPassword(), BCrypt.gensalt()));

        User userSaved = userRepository.save(user);

        return UserRegistrationResponse.builder()
                .id(userSaved.getId())
                .email(userSaved.getEmail())
                .name(userSaved.getName())
                .password(userSaved.getPassword())
                .status("CREATED")
                .build();
    }

    public User setOrganization(String id, Organization organization) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new RuntimeException("Invalid authentication");

        optionalUser.get().getOrganizations().add(organization);
        optionalUser.get().setRole(Role.ORGANIZATION_ADMIN);

        userRepository.save(optionalUser.get());

        return optionalUser.get();
    }

    public User getAuthentication(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new RuntimeException("Invalid authentication");

        return optionalUser.get();
    }

    public UserProfileResponse getProfile(String id) {
        User user = getAuthentication(id);

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

    public UserProfileResponse patchProfile(UserProfileRequest userProfileRequest, String id) {
        User user = getAuthentication(id);

        if(userProfileRequest.getAddress() != null) user.setAddress(userProfileRequest.getAddress());
        if (userProfileRequest.getInstagram() != null) user.setInstagram(userProfileRequest.getInstagram());
        if(userProfileRequest.getTwitter() != null) user.setTwitter(userProfileRequest.getTwitter());
        if (userProfileRequest.getCountry() != null) user.setCountry(userProfileRequest.getCountry());

        User userSaved = userRepository.save(user);

        return UserProfileResponse.builder()
                .name(userSaved.getName())
                .email(userSaved.getEmail())
                .phone(userSaved.getPhone())
                .address(userSaved.getAddress())
                .country(userSaved.getCountry())
                .instagram(userSaved.getInstagram())
                .twitter(userSaved.getTwitter())
                .build();
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.getUserByEmail(email);
        if(user.isEmpty()) throw new RuntimeException("User not found");

        return user.get();
    }

    public User joinOrganization(String id, Organization organization) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new RuntimeException("Invalid authentication");

        optionalUser.get().getOrganizations().add(organization);
        optionalUser.get().setRole(Role.ORGANIZATION_MEMBER);

        userRepository.save(optionalUser.get());

        return optionalUser.get();
    }
}
