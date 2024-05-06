package com.marco.Esportify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationMembersRequest {
    private String name;
    private String email;
    private String phone;
    private String role;
}
