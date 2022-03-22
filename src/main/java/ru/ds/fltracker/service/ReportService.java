package ru.ds.fltracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ds.fltracker.entity.SessionEntity;
import ru.ds.fltracker.strategy.report.ReportGenerateStrategy;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final List<ReportGenerateStrategy> reportStrategies;

    public void generateReport(List<SessionEntity> sessions, ReportGenerateStrategy.ReportPattern pattern) {
        try {
            getGenerateStrategy(pattern).generate(sessions);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private ReportGenerateStrategy getGenerateStrategy(ReportGenerateStrategy.ReportPattern pattern) {
        return reportStrategies.stream()
                .filter(s -> s.isApplicable(pattern))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Unknown pattern '%s'", pattern)));
    }
}
