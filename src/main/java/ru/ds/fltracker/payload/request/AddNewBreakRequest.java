package ru.ds.fltracker.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ds.fltracker.dto.BreakDto;

@Data
@NoArgsConstructor
public class AddNewBreakRequest {
    private Long sessionId;
    private BreakDto breakPayload;
}
