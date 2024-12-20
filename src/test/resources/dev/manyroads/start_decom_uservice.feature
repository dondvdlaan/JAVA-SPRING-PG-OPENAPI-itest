Feature: Start decom uservice

  Background:
    Given starting up wiremockserver Decom

  Scenario Outline: Start Decom by sending a matter request
    Given admin client source delivers "<vehicle>"
    And Decom customer process client accepts charge
    When Start Decom with matter request <customerNr> "<matterNr>" "<terminationCallBackUrl>"
    Then Decom returns <customerNr>
    Then stopping wiremockserver Decom

    Examples:
      | vehicle   | customerNr | matterNr | terminationCallBackUrl |
      | bulldozer | 123456     | 98765    | /v1/terminate-matter/   |
      | dirtbike  | 1111111    | 22222    | /v1/terminate-matter/   |


