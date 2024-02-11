Feature: Task Form
  Scenario: Submitting the task form
    Given I am on the task form page
    When I enter the name "Saad Zafar"
    And I enter the author "Saad Zafar"
    And I enter the price "1000"
    And I click the Save button
    Then I should see a success message
