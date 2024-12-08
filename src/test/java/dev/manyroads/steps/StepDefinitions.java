package dev.manyroads.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import net.minidev.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {

    Response response;

    @When("Start Decom with matter request")
    public void startDCMWithMatterRequest() {

        RestAssured.baseURI = "http://localhost:8080/v1";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("customerNr", 121212);
        jsonObject.put("matterNr", "9781449325862");
        request.body(jsonObject.toJSONString());

        // Activate
        response = request.post("/matters");
    }

    @Then("Bericht terugontvangen")
    public void theScenarioPasses() {
        assertEquals("121212",getJsonPath(response,"customerNr"));
    }

    static String getJsonPath(Response response, String key) {
        String complete = response.asString();
        JsonPath js = new JsonPath(complete);
        return js.get(key).toString();
    }
}
