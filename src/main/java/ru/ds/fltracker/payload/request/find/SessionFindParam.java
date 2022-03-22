package ru.ds.fltracker.payload.request.find;

import io.swagger.annotations.ApiParam;
import lombok.*;
import org.springframework.data.domain.Sort;
import ru.ds.fltracker.dto.UserDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class SessionFindParam {
    @ApiParam(value = "Идентификатор пользователя")
    private Integer userId;
    @ApiParam(value = "Дата создания с")
    private LocalDateTime fromDate;
    @ApiParam(value = "Дата создания по")
    private LocalDateTime toDate;
    @ApiParam(value = "Номер страницы", required = true)
    private int pageNumber;
    @ApiParam(value = "Размер страницы", required = true)
    private int pageSize;
    @ApiParam(value = "Направление сортировки")
    private Sort.Direction sortDirection;
    @ApiParam(value = "Поле сортировки")
    private String sortProperty;
}
