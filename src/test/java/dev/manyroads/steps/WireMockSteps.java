package dev.manyroads.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.en.Then;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class WireMockSteps {

    static WireMockServer wireMockServer;

    @Given("starting up wiremockserver")
    public void startUp() {
        int port = 7090;
        wireMockServer = new WireMockServer(options().port(port));
        wireMockServer.start();
    }

    @Given("admin client source delivers {string}")
    public void adminClientDeliversFollowingVehicle(String vehicle) {

        wireMockServer.stubFor(get(urlMatching("/vehicles/([0-9]*)"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(vehicle)));
    }

    @And("customer process client accepts charge")
    public void customerProcessClientAcceptsCharge() {
        wireMockServer.stubFor(post(urlEqualTo("/v1/process_charge"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)));
    }


    @Then("stopping wiremockserver")
    public void stoppingWiremockserver() {
        wireMockServer.stop();
    }
}
