package ru.ds.fltracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ds.fltracker.entity.ScreenshotEntity;
import ru.ds.fltracker.entity.SessionEntity;
import ru.ds.fltracker.payload.request.SaveNewScreenshotRequest;
import ru.ds.fltracker.repo.ScreenshotRepo;

import java.io.FileInputStream;

@Service
@Transactional
@RequiredArgsConstructor
public class ScreenshotService {
    private final ScreenshotRepo screenshotRepo;

    public ScreenshotEntity saveNewScreenshot(SessionEntity session, FileInputStream screenshot) {
        return null;
    }
}
