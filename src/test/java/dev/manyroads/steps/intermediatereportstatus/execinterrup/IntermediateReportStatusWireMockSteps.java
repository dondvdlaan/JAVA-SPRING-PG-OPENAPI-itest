package dev.manyroads.steps.intermediatereportstatus.execinterrup;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class IntermediateReportStatusWireMockSteps {

    static WireMockServer wireMockServer;

    @Given("starting up wiremockserver Intermediate Report Status")
    public void startUp() {
        int port = 7090;
        wireMockServer = new WireMockServer(options().port(port));
        wireMockServer.start();
    }

    @Given("admin client DCM_APPLIED returns {string}")
    public void adminClientStartDCMApplied(String returnMessage) {

        wireMockServer.stubFor(post(urlEqualTo("/applied"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(returnMessage)));
    }

    @Then("stopping wiremockserver Intermediate Report")
    public void stoppingWiremockserver() {
        wireMockServer.stop();
    }
}
