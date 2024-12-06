package dev.manyroads.steps;

import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

    String testURL;

    @Given("Test URL")
    public void anExampleScenario() {
        this.testURL="http://localhost/8080/test";
    }

    @When("Stuur een GET bericht")
    public void allStepDefinitionsAreImplemented() {
        System.out.println("testURL: " + testURL);
    }

    @Then("Bericht terugontvangen")
    public void theScenarioPasses() {
        assertEquals("http://localhost/8080/test",this.testURL);
    }

}
