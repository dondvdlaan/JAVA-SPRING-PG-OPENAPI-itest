Feature: Start decom uservice

  Background:
    Given starting up wiremockserver

  Scenario Outline: Start Decom by sending a matter request
    Given admin client source delivers "<vehicle>"
    And customer process client accepts charge
    When Start Decom with matter request <customerNr> "<matterNr>"
    Then Decom returns <customerNr>
    Then stopping wiremockserver

    Examples:
      | vehicle   | customerNr | matterNr |
      | bulldozer | 123456     | 98765    |
      | dirtbike  | 1111111    | 22222    |


