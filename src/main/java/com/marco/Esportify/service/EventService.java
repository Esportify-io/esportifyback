package com.marco.Esportify.service;

import com.marco.Esportify.domain.Event;
import com.marco.Esportify.domain.Team;
import com.marco.Esportify.model.EventCreateRequest;
import com.marco.Esportify.model.EventCreateResponse;
import com.marco.Esportify.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final TeamService teamService;

    public EventCreateResponse create(EventCreateRequest eventCreateRequest) {

        Team team = teamService.getTeam(eventCreateRequest.getTeam_id());

        Event event = Event.builder()
                .event_name(eventCreateRequest.getEvent_name())
                .team(team)
                .localDate(eventCreateRequest.getLocalDate())
                .build();

        Event savedEvent = eventRepository.save(event);

        return EventCreateResponse.builder()
                .id(savedEvent.getId())
                .event(savedEvent)
                .build();
    }

    public List<EventCreateResponse> getEvents() {

        List<Event> events = eventRepository.findAll();

        return events.stream()
                .map(e -> EventCreateResponse.builder()
                        .id(e.getId())
                        .event(e)
                        .build())
                .toList();
    }
}
