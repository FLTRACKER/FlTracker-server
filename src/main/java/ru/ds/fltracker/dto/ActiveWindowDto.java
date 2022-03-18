package ru.ds.fltracker.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@ApiModel("Модель активного окна")
public class ActiveWindowDto {
    @ApiModelProperty(
            "Имя окна"
    )
    private String title;

    @ApiModelProperty(
            "Время в окне"
    )
    private LocalTime totalTime;
}
