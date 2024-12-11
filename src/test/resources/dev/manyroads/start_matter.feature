Feature: Start matter

  Scenario Outline: Start Decom by sending a matter request
    Given admin client source delivers "<vehicle>"
    Given customer process client accepts charge
    When Start Decom with matter request
    Then Bericht terugontvangen

    Examples:
      | vehicle     |
      | bulldozer |


