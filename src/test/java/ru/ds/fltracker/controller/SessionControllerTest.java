package ru.ds.fltracker.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ds.fltracker.BaseTest;
import ru.ds.fltracker.dto.SessionDto;
import ru.ds.fltracker.service.SessionService;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SessionControllerTest extends BaseTest {

    @Autowired
    private ObjectMapper objectMapper;
    @SpyBean
    private SessionService sessionService;

    @SneakyThrows
    @Test
    public void createSessionTest() {
        //when(sessionService.findAll()).thenReturn(Collections.emptyList());

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(URI.create("/api/sessions"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("@.length()", IsEqual.equalTo(1)))
                .andReturn()
                .getResponse();
        String contentAsString = response.getContentAsString();
        int x = 0;

        //List<SessionDto> sessionDto = objectMapper.readValue(response.getContentAsString(), new TypeReference<List<SessionDto>>() {});
    }
}
