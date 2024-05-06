package com.marco.Esportify.model;

import com.marco.Esportify.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationMembersResponse {
    private String id;
    private String name;
    private String role;
    private List<OrganizationMembersRequest> organizationMembersRequest;
}
