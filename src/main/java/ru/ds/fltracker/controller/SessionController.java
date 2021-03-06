package ru.ds.fltracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ds.fltracker.dto.SessionDto;
import ru.ds.fltracker.entity.SessionEntity;
import ru.ds.fltracker.mapper.Mapper;
import ru.ds.fltracker.payload.request.GenerateWorkReportRequest;
import ru.ds.fltracker.service.SessionService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sessions")
public class SessionController {
    private final SessionService sessionService;
    private final Mapper mapper;

    @PostMapping
    public ResponseEntity<Long> createNewSession(@RequestBody SessionDto sessionDto) {
        SessionEntity newSession = sessionService.save(mapper.map(sessionDto, SessionEntity.class));
        return ResponseEntity.ok(newSession.getId());
    }

    @PutMapping("/finish/{id}")
    public ResponseEntity<Boolean> finishSession(@PathVariable("id") SessionEntity sessionEntity) {
        return ResponseEntity.ok(sessionService.finishSession(sessionEntity));
    }

    @PostMapping("/report")
    public ResponseEntity<Void> generateWorkReport(@RequestBody @Valid GenerateWorkReportRequest request) {
        sessionService.generateWorkReport(request);
        return ResponseEntity.ok().build();
    }

    // TODO: Change on pagination
    @GetMapping
    public ResponseEntity<List<SessionDto>> findAll() {
        return ResponseEntity.ok(mapper.mapAsList(sessionService.findAll(), SessionDto.class));
    }
}
