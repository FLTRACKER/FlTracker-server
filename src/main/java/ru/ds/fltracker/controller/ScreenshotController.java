package ru.ds.fltracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/screenshots")
public class ScreenshotController {
    private final ScreenshotService screenshotService;
    private final SessionService sessionService;
    private final Mapper mapper;

    @PostMapping(headers = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<ScreenshotDto> saveNewScreenshot(@RequestBody Long sessionId, @RequestPart("file") MultipartFile file) {
        SessionEntity session = sessionService.findSessionById(sessionId);
        return ResponseEntity.ok(mapper.map(screenshotService.saveNewScreenshot(session, file), ScreenshotDto.class));
    }

    @GetMapping("/sessionsScreenshots/{id}")
    public List<ScreenshotDto> getSessionScreenshots(@PathVariable("id") SessionEntity sessionEntity) {
        return mapper.mapAsList(screenshotService.getSessionScreenshots(sessionEntity), ScreenshotDto.class);
    }
}
