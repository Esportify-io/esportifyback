package com.marco.Esportify.model;

import com.marco.Esportify.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventCreateResponse {
    private Long id;
    private Event event;
}
