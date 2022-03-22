package ru.ds.fltracker.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class GenerateWorkReportRequest {
    @NotNull
    private LocalDateTime fromDate;
    @NotNull
    private LocalDateTime toDate;
}