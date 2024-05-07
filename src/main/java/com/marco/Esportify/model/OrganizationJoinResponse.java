package com.marco.Esportify.model;

import com.marco.Esportify.domain.Organization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrganizationJoinResponse {
    private Organization organization;
    private String status;
}
