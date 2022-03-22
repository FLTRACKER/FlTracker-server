package ru.ds.fltracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ds.fltracker.dto.BreakDto;
import ru.ds.fltracker.dto.ScreenshotDto;
import ru.ds.fltracker.entity.BreakEntity;
import ru.ds.fltracker.entity.ScreenshotEntity;
import ru.ds.fltracker.entity.SessionEntity;
import ru.ds.fltracker.mapper.Mapper;
import ru.ds.fltracker.payload.request.AddNewBreakRequest;
import ru.ds.fltracker.payload.request.SaveNewScreenshotRequest;
import ru.ds.fltracker.service.ScreenshotService;
import ru.ds.fltracker.service.SessionService;

import javax.imageio.ImageIO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/screenshots")
public class ScreenshotController {
    private final ScreenshotService screenshotService;
    private final SessionService sessionService;
    private final Mapper mapper;

    @PostMapping
    public ResponseEntity<ScreenshotDto> saveNewScreenshot(@RequestBody SaveNewScreenshotRequest request) {
        SessionEntity session = sessionService.findSessionById(request.getSessionId());
        return ResponseEntity.ok(mapper.map(screenshotService.saveNewScreenshot(session, request.getFile()), ScreenshotDto.class));
    }
}
