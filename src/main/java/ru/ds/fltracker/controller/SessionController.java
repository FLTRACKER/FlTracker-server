package ru.ds.fltracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ds.fltracker.dto.SessionDto;
import ru.ds.fltracker.entity.SessionEntity;
import ru.ds.fltracker.mapper.Mapper;
import ru.ds.fltracker.service.SessionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sessions")
public class SessionController {
    private final SessionService sessionService;
    private final Mapper mapper;

    @PostMapping
    public ResponseEntity<String> createNewSession(@RequestBody SessionDto sessionDto) {
        sessionService.save(mapper.map(sessionDto, SessionEntity.class));
        return ResponseEntity.ok("New Session was created");
    }

    // TODO: Change on pagination
    @GetMapping
    public ResponseEntity<List<SessionDto>> findAll() {
        return ResponseEntity.ok(mapper.mapAsList(sessionService.findAll(), SessionDto.class));
    }
}
