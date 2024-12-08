package dev.manyroads.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;


public class StartWM {

    static int port = 7090 ;
    WireMockServer wireMockServer;

    public StartWM() {
        this.wireMockServer =  new WireMockServer(options().port(port));;
    }

    public static void run() {
        StartWM startWM = new StartWM();
        startWM.wireMockServer.stubFor(get(urlEqualTo("/vehicles"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("bulldozer")));
        startWM.wireMockServer.start();
        System.out.printf("wireMockServer started on port %d!%n", port);

    }
}
