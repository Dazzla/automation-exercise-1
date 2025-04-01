Feature: Web Form submission

  Scenario: Submit web form successfully
    Given the user navigates to the web form
    When the user fills out the form with valid data
    And submits the form
    Then the form should display a success message