package dev.manyroads.steps.intermediatereportstatus.execinterrup;

import dev.manyroads.model.dtos.ExecInterrupRequestDTO;
import dev.manyroads.model.dtos.IntermediateReportMatterRequestDTO;
import dev.manyroads.model.dtos.IntermediateReportStatusRequestDTO;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import wiremock.net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartIntermediateReportStatusStep {

    Response response;

    @When("Start Intermediate Report with request {string}")
    public void startIntermediateReportStatusRequest(String statusIntermediateReport) {

        RestAssured.baseURI = "http://localhost:8080/v1";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        // Here you can either send a DTO class or a JSONObject
        List<IntermediateReportMatterRequestDTO> mattersIntermediateReport = new ArrayList<>();
        IntermediateReportMatterRequestDTO intermediateReportMatterRequestDTO1
                = new IntermediateReportMatterRequestDTO("12345", "expired");
        IntermediateReportMatterRequestDTO intermediateReportMatterRequestDTO2
                = new IntermediateReportMatterRequestDTO("67890", "released");
        mattersIntermediateReport.add(intermediateReportMatterRequestDTO1);
        mattersIntermediateReport.add(intermediateReportMatterRequestDTO2);
        IntermediateReportStatusRequestDTO intermediateReportStatusRequestDTO =
                new IntermediateReportStatusRequestDTO(
                        UUID.randomUUID(),
                        statusIntermediateReport, mattersIntermediateReport);
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put("customerNr", customerNr);
        //jsonObject.put("execInterrupType", execInterrupEnum);
        jsonObject.put("matterNr", null);
        //request.body(jsonObject.toJSONString());
        System.out.println("intermediateReportStatusRequestDTO " + intermediateReportStatusRequestDTO);
        request.body(intermediateReportStatusRequestDTO);

        // Activate
        response = request.post("/charges/intermediatereportstatus");
        System.out.println("response " + response.getStatusLine());
        System.out.println("response " + response.getBody().asString());
    }

    @Then("Intermediate Report returns {string}")
    public void theScenarioPasses(String returnCode) {
        assertEquals(returnCode, getJsonPath(response, "status"));
    }

    static String getJsonPath(Response response, String key) {
        String complete = response.asString();
        JsonPath js = new JsonPath(complete);
        return js.get(key).toString();
    }


}
