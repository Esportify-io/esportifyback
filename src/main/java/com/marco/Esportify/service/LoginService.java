package com.marco.Esportify.service;

import com.marco.Esportify.domain.User;
import com.marco.Esportify.model.LoginRequest;
import com.marco.Esportify.model.LoginResponse;
import com.marco.Esportify.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isEmpty()) throw new RuntimeException("Wrong credentials");

        if (!BCrypt.checkpw(loginRequest.getPassword(), user.get().getPassword()))
            throw new RuntimeException("Wrong credentials");

        JwtClaimsSet admin = JwtClaimsSet.builder()
                .issuer("Mosaic")
                .subject(user.get().getId())
                .claim("scope", "user")
                .claim("name", user.get().getName())
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(admin)).getTokenValue();

        return LoginResponse.builder()
                .token(token)
                .build();
    }
}
