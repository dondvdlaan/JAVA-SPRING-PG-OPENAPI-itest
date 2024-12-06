package dev.manyroads.wiremock;

import org.springframework.stereotype.Service;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

@Service
public class WireMockService {

    static String URL_DECOM = "http://localhost:8080/vehicles";

    public void runStubs(){

    stubFor(get(urlEqualTo(URL_DECOM))
            .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("dirtbike")
                ));
    }
}
