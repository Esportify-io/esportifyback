package com.marco.Esportify.model;

import com.marco.Esportify.domain.Organization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticationResponse {
    private String name;
    private List<Organization> organization;
}
