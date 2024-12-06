package dev.manyroads.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

//@Configuration
public class WireMockConfig {

    public WireMockServer mockServer() {
        return new WireMockServer(WireMockConfiguration.options().port(8082));
    }
}
