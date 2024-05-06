package com.marco.Esportify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileRequest {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String twitter;
    private String instagram;
    private String country;
}
