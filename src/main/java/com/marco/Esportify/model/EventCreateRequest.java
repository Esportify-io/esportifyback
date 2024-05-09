package com.marco.Esportify.model;

import com.marco.Esportify.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventCreateRequest {
    private Long team_id;
    private String event_name;
    private LocalDate localDate;
}
