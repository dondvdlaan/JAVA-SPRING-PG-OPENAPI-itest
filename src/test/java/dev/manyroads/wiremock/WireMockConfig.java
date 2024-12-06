package dev.manyroads.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WireMockConfig {

    @Bean
    public WireMockServer mockServer() {
        return new WireMockServer(WireMockConfiguration.options().port(8082));
    }
}
