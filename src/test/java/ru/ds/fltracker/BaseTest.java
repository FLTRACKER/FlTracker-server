package ru.ds.fltracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.ds.fltracker.config.TestConfig;
import ru.ds.fltracker.entity.BreakEntity;
import ru.ds.fltracker.entity.SessionEntity;
import ru.ds.fltracker.entity.UserEntity;
import ru.ds.fltracker.service.BreakService;
import ru.ds.fltracker.service.SessionService;
import ru.ds.fltracker.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Collections;

@SpringBootTest
@ActiveProfiles({"test"})
@Testcontainers
@Import({TestConfig.class})
@ContextConfiguration(initializers = {BaseTest.Initializer.class})
@DirtiesContext
public class BaseTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private RestTemplate restTemplate;

    protected MockRestServiceServer mockServer;
    protected MockMvc mockMvc;


    @Container
    public static JdbcDatabaseContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:11.1")
                    .withDatabaseName("fttracker")
                    .withUsername("user")
                    .withPassword("password")
                    .withUrlParam("stringtype", "unspecified");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
        this.mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    protected File readFileFromResource(String s) throws IOException {
        Resource resource = new ClassPathResource(s);
        return resource.getFile();
    }

    @BeforeAll
    static void init(@Autowired BreakService breakService,
                     @Autowired SessionService sessionService,
                     @Autowired UserService userService) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("user");
        userService.save(userEntity);

        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setStartTime(LocalDateTime.now());
        sessionEntity.setUser(userEntity);
        sessionService.save(sessionEntity);

        BreakEntity breakEntity = new BreakEntity();
        breakEntity.setStartTime(LocalDateTime.now());
        breakEntity.setFinishTime(LocalDateTime.now().plusMinutes(5));
        breakEntity.setSession(sessionEntity);
        breakService.save(breakEntity);
    }
}
