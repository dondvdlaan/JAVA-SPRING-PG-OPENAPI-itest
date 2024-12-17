package dev.manyroads.steps.intermediatereportstatus.execinterrup;

import dev.manyroads.entities.MatterResponseTestEntity;
import dev.manyroads.entities.MatterResponseTestRepo;
import dev.manyroads.model.dtos.IntermediateReportMatterRequestDTO;
import dev.manyroads.model.dtos.IntermediateReportStatusRequestDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import wiremock.net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartIntermediateReportStatusStep {

    @Autowired
    MatterResponseTestRepo matterResponseTestRepo;

    Response response;
    UUID chargeID;

    @Given("loading charge from customer {int} in test data")
    public void loadChargeFromCustomerInTestData(int customerNr) {
        Optional<MatterResponseTestEntity> matterResponseTestEntity = matterResponseTestRepo.findByCustomerNr(customerNr);
        matterResponseTestEntity
                .ifPresent(responseTestEntity -> chargeID = responseTestEntity.getChargeID());
        System.out.println("chargeID: " + chargeID);
    }

    @When("Start Intermediate Report with request {string}")
    public void startIntermediateReportStatusRequest(String statusIntermediateReport) {

        RestAssured.baseURI = "http://localhost:8080/v1";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        // Here you can either send a DTO class or a JSONObject
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put("customerNr", customerNr);
        //jsonObject.put("execInterrupType", execInterrupEnum);
        jsonObject.put("matterNr", null);
        //request.body(jsonObject.toJSONString());

        request.body(getIntermediateReportStatusRequestDTO(chargeID, statusIntermediateReport));

        // Activate
        response = request.post("/charges/intermediatereportstatus");
        System.out.println("response " + response.getStatusLine());
        System.out.println("response " + response.getStatusCode());
    }

    @Then("Intermediate Report returns {int}")
    public void theScenarioPasses(int returnCode) {
        assertEquals(returnCode, response.getStatusCode());
    }

    static String getJsonPath(Response response, String key) {
        String complete = response.asString();
        JsonPath js = new JsonPath(complete);
        return js.get(key).toString();
    }

    // sub methods
    private static IntermediateReportStatusRequestDTO getIntermediateReportStatusRequestDTO(
            UUID chargeID, String statusIntermediateReport) {
        List<IntermediateReportMatterRequestDTO> mattersIntermediateReport = new ArrayList<>();
        IntermediateReportMatterRequestDTO intermediateReportMatterRequestDTO1
                = new IntermediateReportMatterRequestDTO("12345", "expired");
        IntermediateReportMatterRequestDTO intermediateReportMatterRequestDTO2
                = new IntermediateReportMatterRequestDTO("67890", "released");
        mattersIntermediateReport.add(intermediateReportMatterRequestDTO1);
        mattersIntermediateReport.add(intermediateReportMatterRequestDTO2);
        IntermediateReportStatusRequestDTO intermediateReportStatusRequestDTO =
                new IntermediateReportStatusRequestDTO(
                        chargeID,
                        statusIntermediateReport, mattersIntermediateReport);
        System.out.println("intermediateReportStatusRequestDTO " + intermediateReportStatusRequestDTO);

        return intermediateReportStatusRequestDTO;
    }
}
