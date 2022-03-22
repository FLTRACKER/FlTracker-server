package ru.ds.fltracker.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ds.fltracker.dto.ActiveWindowDto;

@Data
@NoArgsConstructor
public class SaveActiveWindowInfoRequest {
    private ActiveWindowDto activeWindowPayload;
}
