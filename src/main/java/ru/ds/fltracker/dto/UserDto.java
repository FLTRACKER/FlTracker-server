package ru.ds.fltracker.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("Модель пользователя")
public class UserDto {
    @ApiModelProperty(
            "Идентификатор пользователя"
    )
    private Integer id;

    @ApiModelProperty(
            "Имя пользователя"
    )
    private String username;

    @ApiModelProperty(
            "Роли пользователя"
    )
    private List<RoleDto> roles;
}
