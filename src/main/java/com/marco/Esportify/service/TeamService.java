package com.marco.Esportify.service;

import com.marco.Esportify.domain.Organization;
import com.marco.Esportify.domain.Team;
import com.marco.Esportify.domain.User;
import com.marco.Esportify.model.TeamCreateRequest;
import com.marco.Esportify.model.TeamCreateResponse;
import com.marco.Esportify.repository.OrganizationRepository;
import com.marco.Esportify.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserService userService;
    private final OrganizationRepository organizationRepository;


    public TeamCreateResponse create(TeamCreateRequest teamCreateRequest, String id) {
        User user = userService.getAuthentication(id);

        Organization organization = user.getOrganizations().get(0);

        Team team = Team.builder()
                .name(teamCreateRequest.getName())
                .members(List.of())
                .organization(organization)
                .build();

        organization.getTeams().add(team);

        Team teamSaved = teamRepository.save(team);

        organizationRepository.save(organization);

        return TeamCreateResponse.builder()
                .status("SUCCESS")
                .team(teamSaved)
                .build();
    }
}
