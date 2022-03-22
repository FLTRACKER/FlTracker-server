package ru.ds.fltracker.strategy.report;

import ru.ds.fltracker.entity.SessionEntity;

import java.io.FileNotFoundException;
import java.util.List;

public interface ReportGenerateStrategy {
    boolean isApplicable(ReportPattern pattern);

    void generate(List<SessionEntity> sessions) throws Exception;

    enum ReportPattern {
        PLAIN
    }
}
