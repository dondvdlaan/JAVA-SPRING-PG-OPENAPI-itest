Feature: Start Exec Interrup Service

  Background:
    Given starting up wiremockserver Exec Interrup

  Scenario Outline: Send exec interrup message customer deceased
    Given admin client returns "<returnMessage>"
    Given loading terminationCallBackUrl test data for customer <customerNr>
    Given parent microservice receives correctly termination request and returns "<returnMessage>"
    When Start exec interrup with request <customerNr> "<execInterrupType>"
    Then Exec Interrup returns <customerNr>
    Then stopping wiremockserver Exec Interrup

    Examples:
      | returnMessage | customerNr | execInterrupType  |
      | ok            | 123456     | customer_deceased |


