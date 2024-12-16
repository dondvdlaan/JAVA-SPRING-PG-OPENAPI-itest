package dev.manyroads.steps.decom;

import dev.manyroads.entities.MatterResponseTestEntity;
import dev.manyroads.entities.MatterResponseTestRepo;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wiremock.net.minidev.json.JSONObject;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@Service
public class StartDecomStep {

    @Autowired
    MatterResponseTestRepo matterResponseTestRepo;
    Response response;

    @When("Start Decom with matter request {int} {string}")
    public void startDCMWithMatterRequest(int customerNr, String matterNr) {

        RestAssured.baseURI = "http://localhost:8080/v1";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("customerNr", customerNr);
        jsonObject.put("matterNr", matterNr);
        request.body(jsonObject.toJSONString());

        // Activate
        response = request.post("/matters");
    }

    @Then("Decom returns {int}")
    public void decomReturnCustomerNr(int customerNr) {
        System.out.println("decomReturnCustomerNr");
        assertEquals(Integer.toString(customerNr), getJsonPath(response, "customerNr"));

        System.out.println("decomReturnCustomerNr: saving matterResponseTestEntity ");
        MatterResponseTestEntity matterResponseTestEntity = MatterResponseTestEntity.builder()
                .customerNr(Long.parseLong(getJsonPath(response, "customerNr")))
                .chargeID(UUID.fromString(getJsonPath(response, "chargeID")))
                .build();
        var saved = matterResponseTestRepo.save(matterResponseTestEntity);
        System.out.println("decomReturnCustomerNr saved :" + saved);

    }

    // sub methods
    static String getJsonPath(Response response, String key) {
        String complete = response.asString();
        JsonPath js = new JsonPath(complete);
        return js.get(key).toString();
    }

}
