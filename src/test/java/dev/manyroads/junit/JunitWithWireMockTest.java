package dev.manyroads.junit;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class JunitWithWireMockTest {

    CloseableHttpClient httpClient;
    WireMockServer wireMockServer;

    @BeforeEach
    void setUp() {
        httpClient = HttpClients.createDefault();
        wireMockServer = new WireMockServer(8090);
        wireMockServer.stop();
        wireMockServer.start();
        setupStub();
    }

    public void setupStub() {
        // AdminClient
        wireMockServer.stubFor(get(urlEqualTo("/vehicles"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("dirtbike")
                )
        );
    }

    @Test
    void startDecomHappyFlow() throws IOException, ParseException {
        // Prepare


        // When
        HttpGet request = new HttpGet("http://localhost:8080/test");
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);

        // Verify
        assertEquals(result, "Holita");
    }

    @AfterEach
    void simmerDown() {
    }

}
