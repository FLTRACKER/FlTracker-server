package ru.ds.fltracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ds.fltracker.dto.ActiveWindowDto;
import ru.ds.fltracker.dto.ActivityDto;
import ru.ds.fltracker.entity.ActiveWindowEntity;
import ru.ds.fltracker.entity.ActivityEntity;
import ru.ds.fltracker.entity.SessionEntity;
import ru.ds.fltracker.mapper.Mapper;
import ru.ds.fltracker.payload.request.AddNewActivityRequest;
import ru.ds.fltracker.payload.request.SaveActiveWindowInfoRequest;
import ru.ds.fltracker.service.ActiveWindowService;
import ru.ds.fltracker.service.ActivityService;
import ru.ds.fltracker.service.SessionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityService activityService;
    private final ActiveWindowService activeWindowService;
    private final SessionService sessionService;
    private final Mapper mapper;

    @PostMapping
    public ResponseEntity<ActivityDto> addNewActivityInSession(@RequestBody AddNewActivityRequest request) {
        ActivityEntity newActivity = mapper.map(request.getActivityPayload(), ActivityEntity.class);
        SessionEntity session = sessionService.findSessionById(request.getSessionId());
        newActivity.setSession(session);
        return ResponseEntity.ok(mapper.map(activityService.save(newActivity), ActivityDto.class));
    }

    @PostMapping("/{id}/addActiveWindow")
    public ResponseEntity<ActiveWindowDto> saveActiveWindowInfo(@PathVariable("id") ActivityEntity activityEntity, @RequestBody ActiveWindowDto activeWindow) {
        ActiveWindowEntity windowInfo = mapper.map(activeWindow, ActiveWindowEntity.class);
        windowInfo.setActivity(activityEntity);
        return ResponseEntity.ok(mapper.map(activeWindowService.save(windowInfo), ActiveWindowDto.class));
    }
}
