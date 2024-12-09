package dev.manyroads.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import wiremock.net.minidev.json.JSONObject;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {

    Response response;

    @When("Start Decom with matter request {int} {string}")
    public void startDCMWithMatterRequest(int customerNr,String matterNr) {

        RestAssured.baseURI = "http://localhost:8080/v1";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("customerNr", customerNr);
        System.out.println("matterNr: "+ matterNr);
        jsonObject.put("matterNr", matterNr);
        request.body(jsonObject.toJSONString());

        // Activate
        response = request.post("/matters");
    }

    @Then("Decom returns {int}")
    public void theScenarioPasses(int customerNr ) {
        assertEquals(Integer.toString(customerNr),getJsonPath(response,"customerNr"));
    }

    static String getJsonPath(Response response, String key) {
        String complete = response.asString();
        JsonPath js = new JsonPath(complete);
        return js.get(key).toString();
    }


}
