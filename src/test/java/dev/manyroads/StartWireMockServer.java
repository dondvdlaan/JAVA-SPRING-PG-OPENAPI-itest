package dev.manyroads;

import com.github.tomakehurst.wiremock.WireMockServer;
import dev.manyroads.config.WMConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StartWireMockServer {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StartWireMockServer.class, args);
        WireMockServer wireMockServer = context.getBean(WireMockServer.class);
        wireMockServer.start();
        System.out.printf("WM started at port %d\n", 7090);
    }

}
