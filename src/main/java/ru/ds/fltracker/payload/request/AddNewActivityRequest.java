package ru.ds.fltracker.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ds.fltracker.dto.ActivityDto;

@Data
@NoArgsConstructor
public class AddNewActivityRequest {
    private Long sessionId;
    private ActivityDto activityPayload;
}
