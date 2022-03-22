package ru.ds.fltracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.ds.fltracker.entity.ActivityEntity;
import ru.ds.fltracker.entity.ScreenshotEntity;
import ru.ds.fltracker.entity.SessionEntity;
import ru.ds.fltracker.payload.request.SaveNewScreenshotRequest;
import ru.ds.fltracker.repo.ScreenshotRepo;

import javax.persistence.EntityManager;
import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScreenshotService {
    private final ScreenshotRepo screenshotRepo;
    private final EntityManager em;
    @Value("${screenshots.path}")
    private String defaultPath;

    public ScreenshotEntity saveNewScreenshot(SessionEntity session, MultipartFile multipartFile) {

        ScreenshotEntity screenshotEntity = new ScreenshotEntity();
        screenshotEntity.setSession(session);
        screenshotEntity.setCreatedTime(LocalDateTime.now());
        save(screenshotEntity);

        File uploadDir = new File(String.format("%s%d", defaultPath, session.getId()));
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String destination = String.format("%s%d/%d.jpg", defaultPath, session.getId(), screenshotEntity.getId());
        File file = new File(destination);
        try {
            multipartFile.transferTo(file);
            screenshotEntity.setPath(destination);
            save(screenshotEntity);
        } catch (IOException e) {
            deleteScreenshot(screenshotEntity.getId());
            e.printStackTrace();
        }
        return screenshotEntity;
    }

    public ScreenshotEntity save(ScreenshotEntity screenshotEntity) {
        ScreenshotEntity persisted = screenshotRepo.saveAndFlush(screenshotEntity);
        em.refresh(persisted);
        return persisted;
    }

    public List<ScreenshotEntity> getSessionScreenshots(SessionEntity sessionEntity) {
        return screenshotRepo.findAllBySession(sessionEntity);
    }

    public void deleteScreenshot(Long id) {
        screenshotRepo.deleteById(id);
    }
}
