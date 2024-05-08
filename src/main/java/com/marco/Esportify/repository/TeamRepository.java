package com.marco.Esportify.repository;

import com.marco.Esportify.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
