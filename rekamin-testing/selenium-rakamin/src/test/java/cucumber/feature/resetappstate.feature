Feature: ResetAppState functionality

  Scenario Outline: Ensure reset app state functionality
    Given user is on saucedemo login page for ras
    When user input <user_name> as user_name for ras
    And user input <password> as password for ras
    And user click submit for ras
    Then user login result for ras
    And user click Add to cart on some product
    Then user show sidebar ras
    Then user click button ras
    Then user verify <status> ras
    Examples:
      | user_name     | password     | status  |
      | standard_user | secret_sauce | success |