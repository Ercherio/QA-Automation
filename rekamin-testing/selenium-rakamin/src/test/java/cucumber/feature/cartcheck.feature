Feature: cartcheck functionality

  Scenario Outline: Ensure cart functionality
    Given user have to login first to cart
    When user input <user_name> as user_name to cart
    And user input <password> as password to cart
    And user click submit to cart
    And user click Add to cart on all product to cart
    And user click cart button
    Then user verify <status> cart result
    Examples:
      | user_name | password | status |
      | standard_user | secret_sauce | success |