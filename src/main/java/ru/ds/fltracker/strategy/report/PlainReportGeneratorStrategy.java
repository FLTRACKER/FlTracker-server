package ru.ds.fltracker.strategy.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.ds.fltracker.entity.ScreenshotEntity;
import ru.ds.fltracker.entity.SessionEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class PlainReportGeneratorStrategy implements ReportGenerateStrategy {
    @Value("${report.path}")
    private String reportPath;

    @Override
    public boolean isApplicable(ReportPattern pattern) {
        return pattern.equals(ReportPattern.PLAIN);
    }

    @Override
    public void generate(List<SessionEntity> sessions) throws Exception {
        File uploadDir = new File(reportPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(String.format("%stest%s.pdf", reportPath, LocalDate.now())));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Офигительный отчёт", font);
        document.add(chunk);

        for (SessionEntity session : sessions) {
            for (ScreenshotEntity screenshot : session.getScreenshots()) {
                Path path = Paths.get(screenshot.getPath());
                Image img = Image.getInstance(path.toAbsolutePath().toString());
                document.add(img);
                Chunk screenshotName = new Chunk(screenshot.getCreatedTime().toString(), font);
                document.add(screenshotName);
            }
        }

        document.close();
    }
}
