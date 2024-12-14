Feature: Start decom uservice

  Background:
    Given starting up wiremockserver exec interrup

  Scenario Outline: Send exec interrup message customer deceased
    Given admin client returns "<returnMessage>"
    When Start exec interrup with matter request <customerNr> "<matterNr>"
    Then Decom returns <customerNr>
    Then stopping wiremockserver

    Examples:
      | returnMessage | customerNr | matterNr |
      | ok            | 123456     | 98765    |
      | dirtbike      | 1111111    | 22222    |


