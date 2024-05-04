package com.marco.Esportify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationResponse {
    private String id;
    private String name;
    private String email;
    private String password;
    private String status;
}
