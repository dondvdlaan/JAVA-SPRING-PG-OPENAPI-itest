Feature: Start matter

  Scenario Outline: Start Decom by sending a matter request
    Given remote data source delivers "<vehicle>"
    When Start Decom with matter request
    Then Bericht terugontvangen

    Examples:
      | vehicle     |
      | bulldozer |


