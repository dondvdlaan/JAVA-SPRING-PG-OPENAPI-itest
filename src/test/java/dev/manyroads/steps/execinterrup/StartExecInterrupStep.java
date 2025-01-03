package dev.manyroads.steps.execinterrup;

import dev.manyroads.model.dtos.ExecInterrupRequestDTO;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import wiremock.net.minidev.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartExecInterrupStep {

    Response response;


    @When("Start exec interrup with request {int} {string}")
    public void startExecInterrupWithExecInterrupRequest(long customerNr, String execInterrupEnum) {

        RestAssured.baseURI = "http://localhost:8080/v1";
        RequestSpecification request = RestAssured.given().auth().basic("decom", "secret");
        request.header("Content-Type", "application/json");
        // Here you can either send a DTO class or a JSONObject
        ExecInterrupRequestDTO execInterrupRequestDTO = new ExecInterrupRequestDTO(customerNr, execInterrupEnum, null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("customerNr", customerNr);
        jsonObject.put("execInterrupType", execInterrupEnum);
        jsonObject.put("matterNr", null);
        System.out.println("jsonObject.toJSONString() " + jsonObject.toJSONString());
        request.body(jsonObject.toJSONString());
        //request.body(execInterrupRequestDTO);

        // Activate
        response = request.post("/execinterrup");
    }

    @Then("Exec Interrup returns {int}")
    public void theScenarioPasses(int customerNr) {
        assertEquals(Integer.toString(customerNr), getJsonPath(response, "customerNr"));
    }

    static String getJsonPath(Response response, String key) {
        String complete = response.asString();
        JsonPath js = new JsonPath(complete);
        return js.get(key).toString();
    }


}
