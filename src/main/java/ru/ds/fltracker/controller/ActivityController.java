package ru.ds.fltracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<String> addNewActivityInSession(@RequestBody AddNewActivityRequest request) {
        ActivityEntity newActivity = mapper.map(request.getActivityPayload(), ActivityEntity.class);
        SessionEntity session = sessionService.findSessionById(request.getSessionId());
        newActivity.setSession(session);
        activityService.save(newActivity);
        return ResponseEntity.ok(
                String.format("Added new activity in session with id %s", request.getSessionId()));
    }

    @PostMapping
    public ResponseEntity<String> saveActiveWindowInfo(@RequestBody SaveActiveWindowInfoRequest request) {
        ActiveWindowEntity windowInfo = mapper.map(request.getActiveWindowPayload(), ActiveWindowEntity.class);
        ActivityEntity activity = activityService.findActivityById(request.getActivityId());
        windowInfo.setActivity(activity);
        activeWindowService.save(windowInfo);
        return ResponseEntity.ok(
                String.format("Added new active window info in activity with id %s", request.getActivityId()));
    }
}
