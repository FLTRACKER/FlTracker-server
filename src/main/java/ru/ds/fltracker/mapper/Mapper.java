package ru.ds.fltracker.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.ds.fltracker.dto.*;
import ru.ds.fltracker.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class Mapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDateTime.class));
        factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));

        factory.classMap(UserEntity.class, UserDto.class)
                .field("id", "id")
                .field("username", "username")
                .field("roles", "roles")
                .byDefault()
                .register();

        factory.classMap(RoleEntity.class, RoleDto.class)
                .field("title", "title")
                .byDefault()
                .register();

        factory.classMap(SessionEntity.class, SessionDto.class)
                .field("startTime", "startTime")
                .field("finishTime", "finishTime")
                .field("user", "user")
                .field("breaks", "breaks")
                .field("screenshots", "screenshots")
                .field("activities", "activities")
                .byDefault()
                .register();

        factory.classMap(ScreenshotEntity.class, ScreenshotDto.class)
                .field("path", "path")
                .field("createdTime", "createdTime")
                .byDefault()
                .register();

        factory.classMap(BreakEntity.class, BreakDto.class)
                .field("startTime", "startTime")
                .field("finishTime", "finishTime")
                .field("description", "description")
                .byDefault()
                .register();

        factory.classMap(ActivityEntity.class, ActivityDto.class)
                .field("startTime", "startTime")
                .field("finishTime", "finishTime")
                .field("activeWindows", "activeWindows")
                .byDefault()
                .register();

        factory.classMap(ActiveWindowEntity.class, ActiveWindowDto.class)
                .field("title", "title")
                .field("totalTime", "totalTime")
                .byDefault()
                .register();
    }
}
