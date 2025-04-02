Feature: Form submission

  Scenario: Submit web form successfully
    Given I am on the form submission page
    When I submit the form with valid data
    Then I see a confirmation message