package com.marco.Esportify.service;

import com.marco.Esportify.domain.Organization;
import com.marco.Esportify.domain.User;
import com.marco.Esportify.model.UserAuthenticationResponse;
import com.marco.Esportify.model.UserRegistrationRequest;
import com.marco.Esportify.model.UserRegistrationResponse;
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

    public void setOrganization(String id, Organization organization) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new RuntimeException("Invalid authentication");

        optionalUser.get().setOrganization(organization);

        userRepository.save(optionalUser.get());
    }

    public UserAuthenticationResponse getAuthentication(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new RuntimeException("Invalid authentication");
        System.out.println(optionalUser.get());

        return UserAuthenticationResponse.builder()
                .name(optionalUser.get().getName())
                .organization(optionalUser.get().getOrganization())
                .build();
    }
}
