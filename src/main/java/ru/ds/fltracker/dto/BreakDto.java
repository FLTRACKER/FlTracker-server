package ru.ds.fltracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("Модель перерывов")
public class BreakDto {
    @ApiModelProperty(
            "Время начала перерыва"
    )
    private LocalDateTime startTime;
    @ApiModelProperty(
            "Время окончания перерыва"
    )
    private LocalDateTime finishTime;
    @ApiModelProperty(
            "Описание причины перерыва"
    )
    private String description;
}
