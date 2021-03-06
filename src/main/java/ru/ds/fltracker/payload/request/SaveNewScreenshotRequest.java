package ru.ds.fltracker.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;

@Data
@NoArgsConstructor
public class SaveNewScreenshotRequest {
    private Long sessionId;
    private MultipartFile file;
}
