package dev.manyroads.spring;

import com.github.tomakehurst.wiremock.WireMockServer;
import dev.manyroads.config.WireMockConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static dev.manyroads.stubs.AdminClientStub.setupAdminClientStubResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("feign")
@EnableConfigurationProperties
@EnableFeignClients
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminClientIntegrationTest {

    @Autowired
    private WireMockServer wireMockServer;

    @BeforeEach
    void setUp() throws IOException {
        setupAdminClientStubResponse(wireMockServer);
    }
    @Test
    public void startCecomHappyFlow() {
        // prepare
        RestAssured.baseURI = "http://localhost:8080/v1";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("customerNr", 121212);
        jsonObject.put("matterNr", "9781449325862");
        request.body(jsonObject.toJSONString());

        // Activate
        Response response = request.post("/matters");

        // Verify
        assertEquals(200,response.getStatusCode());
    }
}
