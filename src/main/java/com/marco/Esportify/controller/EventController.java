package com.marco.Esportify.controller;

import com.marco.Esportify.model.EventCreateRequest;
import com.marco.Esportify.model.EventCreateResponse;
import com.marco.Esportify.service.EventService;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/event")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventCreateResponse> create(@RequestBody EventCreateRequest eventCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.create(eventCreateRequest));
    }

    @GetMapping
    public ResponseEntity<List<EventCreateResponse>> getEvents() {
        return ResponseEntity.ok(eventService.getEvents());
    }
}
