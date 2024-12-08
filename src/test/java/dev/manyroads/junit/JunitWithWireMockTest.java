package dev.manyroads.junit;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
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
        //wireMockServer.stop();
    }

    public void setupStub() {
        wireMockServer.stubFor(get(urlEqualTo("/vehicles"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("bulldozer")));
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
