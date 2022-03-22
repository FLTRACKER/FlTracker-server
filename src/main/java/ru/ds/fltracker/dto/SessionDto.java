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
@ApiModel("Рабочая сессия пользователя")
public class SessionDto {
    @ApiModelProperty(
            "Идентификатор сессии"
    )
    private Long id;
    @ApiModelProperty(
            "Время начала сессии"
    )
    private LocalDateTime startTime;
    @ApiModelProperty(
            "Время завершения сессии"
    )
    private LocalDateTime finishTime;
    @ApiModelProperty(
            "Создатель сессии"
    )
    private UserDto user;
    @ApiModelProperty(
            "Перерывы"
    )
    private List<BreakDto> breaks;
    @ApiModelProperty(
            "Скриншоты"
    )
    private List<ScreenshotDto> screenshots;
    @ApiModelProperty(
            "Активности в пределах сессии"
    )
    private List<ActivityDto> activities;
}
