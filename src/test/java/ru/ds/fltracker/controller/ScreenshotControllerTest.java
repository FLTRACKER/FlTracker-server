package ru.ds.fltracker.controller;

import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.ds.fltracker.BaseTest;
import ru.ds.fltracker.entity.SessionEntity;
import ru.ds.fltracker.payload.request.SaveNewScreenshotRequest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ScreenshotControllerTest extends BaseTest {
    @Autowired
    private ScreenshotController screenshotController;

    @Test
    void saveNewScreenshot() throws IOException {
        FileInputStream input = new FileInputStream(readFileFromResource("screenshots/test.png"));
        MultipartFile multipartFile = new MockMultipartFile("fileItem",
                "file", "image/png", IOUtils.toByteArray(input));
        screenshotController.saveNewScreenshot(1L, multipartFile);

        Assertions.assertEquals(screenshotController.getSessionScreenshots(SessionEntity.of(1L)).size(),1);
    }
}