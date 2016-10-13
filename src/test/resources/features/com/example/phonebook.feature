Feature: Phonebook

  Scenario: Search Faculty Phone Number
    Given I start a new Mainframe session
    When I Open Phonebook
    And Search for faculty name
      | firstname | PRATIK |
    Then I should see the results matching with my search criteria
      | name                 |
      | PRATIK R IYER        |
      | PRATIK KRISHNAN      |