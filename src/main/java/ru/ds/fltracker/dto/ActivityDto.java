package ru.ds.fltracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ApiModel("Модель активности")
public class ActivityDto {
    @ApiModelProperty(
            "Время начала активности"
    )
    private LocalDateTime startTime;
    @ApiModelProperty(
            "Время окончания активности"
    )
    private LocalDateTime finishTime;
    @ApiModelProperty(
            "Информация об активных окнах"
    )
    private List<ActiveWindowDto> activeWindows;
}
