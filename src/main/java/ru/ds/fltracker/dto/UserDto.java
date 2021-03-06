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
            "Идентификатор пользователя (имя пользователя)"
    )
    private String userId;

    @ApiModelProperty(
            "ФИО пользователя"
    )
    private String fullName;

    @ApiModelProperty(
            "Email"
    )
    private String email;
}
