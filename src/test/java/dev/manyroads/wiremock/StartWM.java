package dev.manyroads.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;


public class StartWM {

    static int port = 7090 ;
    static WireMockServer wireMockServer= new WireMockServer(options().port(port));

    /*
    public StartWM() {
        this.wireMockServer =  new WireMockServer(options().port(port));;
    }

    public static void run() {
        StartWM startWM = new StartWM();
        startWM.wireMockServer.stubFor(get(urlMatching("/vehicles/([0-9]*)"))
                        //.withPathParam("matterNr", equalTo("789018"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("bulldozer")));
        startWM.wireMockServer.start();
        System.out.printf("wireMockServer started on port %d!%n", port);

    }

     */
}
