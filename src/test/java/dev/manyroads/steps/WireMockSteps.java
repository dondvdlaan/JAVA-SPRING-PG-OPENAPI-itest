package dev.manyroads.steps;

import io.cucumber.java.en.Given;
import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;


public class WireMockSteps {

    @Given("remote data source delivers {string}")
    public void theRemoteDataSourceDeliversFollowingVehicle(String vehicle) {

        int port = 7090;
        WireMockServer wireMockServer = new WireMockServer(options().port(port));
        wireMockServer.start();
        wireMockServer.stubFor(get(urlMatching("/vehicles/([0-9]*)"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(vehicle)));
    }
}
