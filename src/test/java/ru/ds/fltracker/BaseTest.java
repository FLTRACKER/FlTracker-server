package ru.ds.fltracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.ds.fltracker.entity.BreakEntity;
import ru.ds.fltracker.repo.BreakRepo;
import ru.ds.fltracker.service.BreakService;

@Slf4j
@SpringBootTest
@ActiveProfiles({"test"})
@Testcontainers
@Import({TestJmsConfiguration.class})
@ContextConfiguration(initializers = {BaseTest.Initializer.class})
@DirtiesContext
public class BaseTest {
    protected MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired @Qualifier(DEFAULT_USER_INFO_REST_TEMPLATE_BEAN)
    protected RestTemplate restTemplate;
    protected MockRestServiceServer mockServer;
    @Autowired
    ObjectMapper objectMapper;

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

    @BeforeAll
    static void init(@Autowired BreakService breakService) {
        breakService.save();
    }
}
