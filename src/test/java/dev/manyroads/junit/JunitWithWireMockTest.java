package dev.manyroads.junit;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class JunitWithWireMockTest {

    WireMockServer wireMockServer;

    @BeforeEach
    public void setup () {
        wireMockServer = new WireMockServer(7090);
        wireMockServer.start();
        setupStub();
    }

    @AfterEach
    public void teardown () {
        wireMockServer.stop();
    }

    public void setupStub() {
        wireMockServer.stubFor(get(urlEqualTo("/an/endpoint"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("json/glossary.json")));
    }

    @Test
    public void testStatusCodePositive() {
        given().
                when().
                get("http://localhost:7090/an/endpoint").
                then().
                assertThat().statusCode(200);
    }

    @Test
    public void testStatusCodeNegative() {
        given().
                when().
                get("http://localhost:7090/another/endpoint").
                then().
                assertThat().statusCode(404);
    }

    @Test
    public void testResponseContents() {
        Response response =  given().when().get("http://localhost:7090/an/endpoint");
        //String title = response.jsonPath().get("glossary.title");
        System.out.println(response.asString());
        assertEquals("json/glossary.json", response.asString());
    }
}
