Feature: Logout functionality

  Scenario Outline: Ensure logout functionality
    Given user is on saucedemo login page for logout
    When user input <user_name> as user_name for logout
    And user input <password> as password for logout
    And user click submit for logout
    Then user login result for logout
    Then user show sidebar
    Then user click button logout
    Then user verify <status> logout
    Examples:
      | user_name     | password     | status  |
      | standard_user | secret_sauce | success |