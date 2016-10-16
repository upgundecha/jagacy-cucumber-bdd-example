Feature: Phonebook

  As I university mainframe user
  I should be able to search faculty members in Phonebook
  So I can contact them for help

  Scenario: Search faculty phone number using name
    Given I start a new emulator session
    When I open phonbook application
    And search for faculty name "PRATIK"
    Then I should see the results matching with my search criteria
      |PRATIK R IYER              979-862-1842  LIBRARIES                    5000|
      |PRATIK KRISHNAN             UNAVAILABLE  MARY KAY O'CONNOR PROCESS    3122|