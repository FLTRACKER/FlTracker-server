package ru.ds.fltracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ds.fltracker.dto.BreakDto;
import ru.ds.fltracker.entity.ActivityEntity;
import ru.ds.fltracker.entity.BreakEntity;
import ru.ds.fltracker.entity.SessionEntity;
import ru.ds.fltracker.mapper.Mapper;
import ru.ds.fltracker.payload.request.AddNewActivityRequest;
import ru.ds.fltracker.payload.request.AddNewBreakRequest;
import ru.ds.fltracker.service.BreakService;
import ru.ds.fltracker.service.SessionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/breaks")
public class BreakController {
    private final BreakService breakService;
    private final SessionService sessionService;
    private final Mapper mapper;

    @PostMapping
    public ResponseEntity<BreakDto> addNewBreakInSession(@RequestBody AddNewBreakRequest request) {
        BreakEntity newActivity = mapper.map(request.getBreakPayload(), BreakEntity.class);
        SessionEntity session = sessionService.findSessionById(request.getSessionId());
        newActivity.setSession(session);
        return ResponseEntity.ok(mapper.map(breakService.save(newActivity), BreakDto.class));
    }
}
