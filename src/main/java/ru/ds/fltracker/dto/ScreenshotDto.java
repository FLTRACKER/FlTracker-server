package ru.ds.fltracker.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("Модель скриншота")
public class ScreenshotDto {
    @ApiModelProperty(
            "Путь к скриншоту"
    )
    private String path;
    @ApiModelProperty(
            "Время создание скриншота"
    )
    private LocalDateTime createdTime;
}
