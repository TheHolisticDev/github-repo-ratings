package org.example;

import org.example.util.ClockProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
public class IntegrationTest {

    private static final Clock testClock = Clock.fixed(LocalDate.of(2025,1,1).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

    @MockitoBean
    private ClockProvider clockProvider;

    @BeforeEach
    public void initMocks() {
        when(clockProvider.getClock()).thenReturn(testClock);
    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void health() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/health",
                String.class)).contains("OK");
    }

    @Test
    public void scoredRepositoriesCanBeFetched() throws IOException {
        var expectedResponse = resourceLoader.getResource("classpath:expected-responses/scored-repositories-page-1").getContentAsString(Charset.defaultCharset());
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/scored-repos?createdAfter=2025-01-01&language=java&page=1",
                String.class)).isEqualTo(expectedResponse);
    }

    @Test
    public void differentPagesCanBeFetched() throws IOException {
        var expectedResponsePage2 = resourceLoader.getResource("classpath:expected-responses/scored-repositories-page-2").getContentAsString(Charset.defaultCharset());
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/scored-repos?createdAfter=2025-01-01&language=java&page=2",
                String.class)).isEqualTo(expectedResponsePage2);
        var expectedResponsePage3 = resourceLoader.getResource("classpath:expected-responses/scored-repositories-page-3").getContentAsString(Charset.defaultCharset());
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/scored-repos?createdAfter=2025-01-01&language=java&page=3",
                String.class)).isEqualTo(expectedResponsePage3);
    }

    @Test
    public void samePageCanBeFetchedTwice() throws IOException {
        var expectedResponsePage2 = resourceLoader.getResource("classpath:expected-responses/scored-repositories-page-2").getContentAsString(Charset.defaultCharset());
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/scored-repos?createdAfter=2025-01-01&language=java&page=2",
                String.class)).isEqualTo(expectedResponsePage2);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/scored-repos?createdAfter=2025-01-01&language=java&page=2",
                String.class)).isEqualTo(expectedResponsePage2);
    }
}
