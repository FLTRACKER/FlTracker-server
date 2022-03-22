package ru.ds.fltracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ds.fltracker.payload.request.GenerateWorkReportRequest;
import ru.ds.fltracker.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/report")
    public ResponseEntity<Void> generateWorkReport(@RequestBody @Valid GenerateWorkReportRequest request) {
        userService.generateWorkReport(request);
        return ResponseEntity.ok().build();
    }
}
