package dev.manyroads.steps.execinterrup;

import com.github.tomakehurst.wiremock.WireMockServer;
import dev.manyroads.entities.MatterResponseTestRepo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class ExecInterrupWireMockSteps {

    static WireMockServer wireMockServer;
    @Autowired
    MatterResponseTestRepo matterResponseTestRepo;
    String terminationCallBackUrl;

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

    @Given("loading terminationCallBackUrl test data for customer {int}")
    public void loadingTerminationCallBackUrlTestDataForCustomerCustomerNr(int customerNr) {
        var oMatterResponseTestEntity = matterResponseTestRepo.findByCustomerNr(customerNr);
        oMatterResponseTestEntity.ifPresent(m -> this.terminationCallBackUrl = m.getTerminationCallBackUrl());
    }

    @Given("parent microservice receives correctly termination request and returns {string}")
    public void parentMicroserviceReecievesCorrectlyTerminationRequest(String returnMessage) {
        System.out.println("this.terminationCallBackUrl: " + this.terminationCallBackUrl);
        wireMockServer.stubFor(post(urlEqualTo(terminationCallBackUrl))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(returnMessage)));
    }

    @Then("stopping wiremockserver Exec Interrup")
    public void stoppingWiremockserver() {
        wireMockServer.stop();
    }
}
