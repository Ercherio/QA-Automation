Feature: Login functionality

  Scenario Outline: Ensure login functionality
    Given user is on saucedemo login page
    When user input <user_name> as user_name
    And user input <password> as password
    And user click submit
    Then user verify <status> login result
    Examples:
      | user_name     | password     | status  |
      | standard_user | secret_sauce | success |
      | standar_user  | secret_failed | failed  |