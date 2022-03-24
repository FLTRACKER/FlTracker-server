package ru.ds.fltracker.strategy.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.ds.fltracker.entity.ScreenshotEntity;
import ru.ds.fltracker.entity.SessionEntity;
import ru.ds.fltracker.utils.ReportUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

import static com.itextpdf.text.pdf.PdfDictionary.FONT;

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

        Font font = ReportUtils.getDefaultFont();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(String.format("%stest%s.pdf", reportPath, LocalDate.now())));
        document.open();

        Paragraph titleParagraph = new Paragraph();
        titleParagraph.add(new Chunk("Отчёт", font));
        titleParagraph.setIndentationLeft(50);
        titleParagraph.setIndentationRight(25);
        titleParagraph.setSpacingBefore(50);
        titleParagraph.setSpacingAfter(100);
        document.add(titleParagraph);

        document.newPage();
        for (SessionEntity session : sessions) {
            int imagesCounter = 0;
            for (ScreenshotEntity screenshot : session.getScreenshots()) {
                if (imagesCounter % 2 == 0) {
                    document.newPage();
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
                Chunk screenshotName = new Chunk(screenshot.getCreatedTime().format(formatter), font);
                document.add(screenshotName);

                Paragraph screenshotParagraph = new Paragraph();
                screenshotParagraph.setFirstLineIndent(75);
                screenshotParagraph.setSpacingAfter(20);

                Image img = Image.getInstance(screenshot.getPath());
                float scaler = (((document.getPageSize().getWidth() - document.leftMargin()
                        - document.rightMargin()) / img.getWidth()) * 100);
                img.scalePercent(scaler);

                screenshotParagraph.add(img);
                document.add(screenshotParagraph);
                imagesCounter++;
            }
        }
        document.close();
    }
}
