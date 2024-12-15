Feature: Start Intermediate Report Status Service

  Background:
    Given starting up wiremockserver Intermediate Report Status

  Scenario Outline: Send intermediate report with status DCM_APPLIED
    Given admin client DCM_APPLIED returns "<returnMessage>"
    When Start Intermediate Report with request "<statusIntermediateReport>"
    Then Intermediate Report returns "<returnCode>"
    Then stopping wiremockserver Intermediate Report

    Examples:
      | returnMessage | returnCode | statusIntermediateReport |
      | ok            | 200        | DCM_APPLIED              |


