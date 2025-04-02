Feature: Fields

  Scenario: Field defaults and state
    Given I am on the form submission page
    Then I see that the Text Input is empty
    And the password field is empty
    And the Textarea field is empty
    And the Dropdown (select) is 'Open this select menu'
    And the Dropdown (datalist) is blank
    And the checked checkbox is checked
    And the default checkbox is unchecked
    And the checked radio button is selected
    And the default radio button is unselected
    And the color picker is purple
    And the date picker is empty
    And the slider is centred
    And tbe button is submit
    And the link is to the index page
      



