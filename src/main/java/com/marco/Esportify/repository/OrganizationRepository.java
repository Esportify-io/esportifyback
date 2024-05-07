package com.marco.Esportify.repository;

import com.marco.Esportify.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
    Optional<Organization> findByName(String name);

    Optional<Organization> findByInviteCode(String inviteCode);
}
