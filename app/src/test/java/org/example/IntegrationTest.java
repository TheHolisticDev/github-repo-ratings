package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
public class IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void health() throws IOException {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/health",
                String.class)).contains("OK");
    }

    @Test
    public void scoredRepositoriesCanBeFetched() throws IOException {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/scored-repos?createdAfter=2025-01-01&language=java",
                String.class)).contains("\"https://github.com/EHB-MCT/web1-2024-2025-web1-exercise-01-hello-world-web1-emptysite\":74");
    }
}
