package com.marco.Esportify.model;

import com.marco.Esportify.domain.Organization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationCreateResponse {
    private Organization organization;
    private String status;
}
