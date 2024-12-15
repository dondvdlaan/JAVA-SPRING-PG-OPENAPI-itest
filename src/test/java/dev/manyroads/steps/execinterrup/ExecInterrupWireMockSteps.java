package dev.manyroads.steps.execinterrup;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class ExecInterrupWireMockSteps {

    static WireMockServer wireMockServer;

    @Given("starting up wiremockserver Exec Interrup")
    public void startUp() {
        int port = 7090;
        wireMockServer = new WireMockServer(options().port(port));
        wireMockServer.start();
    }

    @Given("admin client returns {string}")
    public void adminClientTerminatesMatter(String returnMessage) {

        wireMockServer.stubFor(post(urlEqualTo("/terminate-matter"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(returnMessage)));
    }

    @Then("stopping wiremockserver Exec Interrup")
    public void stoppingWiremockserver() {
        wireMockServer.stop();
    }
}
