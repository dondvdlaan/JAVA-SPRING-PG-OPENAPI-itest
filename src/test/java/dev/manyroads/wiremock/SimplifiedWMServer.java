package dev.manyroads.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.stubbing.Scenario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

/**
 * Simplified Quick & Dirty WMServer to be started just by "current file"
 */
public class SimplifiedWMServer {

    private WireMockServer wireMockServer;
    private static final String APPLICATION_JSON = "application/json";
    private static final int HTTP_200 = 200;
    private static final int HTTP_400 = 400;
    private static final int HTTP_500 = 500;
    private static final int PORT = 7090;
    int httpCode;

    @BeforeEach
    void setup() {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(PORT));
        wireMockServer.start();
        WireMock.configureFor("localhost", PORT);
        System.out.printf("[@BeforeEach] wireMockServer started at port %d at %s: \n", PORT, LocalDateTime.now());
    }

    @AfterEach
    void teardown() throws InterruptedException {
        Thread.sleep(100000);
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
        System.out.println("wireMockServer stopped at: " + LocalDateTime.now());
    }

    @Test
    @DisplayName("Miscommunication Retry testing")
    void status500DecomReponse() {
        System.out.println("status500DecomReponse started");
        stubFor(get(urlMatching(("/vehicles/([0-9]*)")))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", APPLICATION_JSON)
                        .withBody("bulldozer")));

        // Returns 500
        stubFor(post(urlEqualTo(("/v1/process_charge")))
                .inScenario("Retry Scenario")
                .whenScenarioStateIs(Scenario.STARTED)
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", APPLICATION_JSON))
                .willSetStateTo("Cause Success"));

        // Returns 200
        stubFor(post(urlEqualTo(("/v1/process_charge")))
                .inScenario("Retry Scenario")
                .whenScenarioStateIs("Cause Success")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", APPLICATION_JSON)));
    }
}


