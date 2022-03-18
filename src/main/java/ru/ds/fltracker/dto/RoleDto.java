package ru.ds.fltracker.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Роль пользователя")
public class RoleDto {
    @ApiModelProperty(
            "Название роли"
    )
    private String title;
}
