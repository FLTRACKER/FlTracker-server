package ru.ds.fltracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping
    public ResponseEntity<ActiveWindowDto> saveActiveWindowInfo(@RequestBody SaveActiveWindowInfoRequest request) {
        ActiveWindowEntity windowInfo = mapper.map(request.getActiveWindowPayload(), ActiveWindowEntity.class);
        ActivityEntity activity = activityService.findActivityById(request.getActivityId());
        windowInfo.setActivity(activity);
        return ResponseEntity.ok(mapper.map(activeWindowService.save(windowInfo), ActiveWindowDto.class));
    }
}
