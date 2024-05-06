package com.marco.Esportify.model;

import com.marco.Esportify.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamCreateResponse {
    private Team team;
    private String status;
}

