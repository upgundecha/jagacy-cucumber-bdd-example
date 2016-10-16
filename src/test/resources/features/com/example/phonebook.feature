Feature: Phonebook

  Scenario: Search Faculty Phone Number
    Given I start a new emulator session
    When I open phonbook application
    And search for faculty name "PRATIK"
    Then I should see the results matching with my search criteria
      |PRATIK R IYER              979-862-1842  LIBRARIES                    5000|
      |PRATIK KRISHNAN             UNAVAILABLE  MARY KAY O'CONNOR PROCESS    3122|