Feature: Add to cart functionality

  Scenario Outline: Ensure add to cart functionality
    Given user have to login first to atc
    When user input <user_name> as user_name to atc
    And user input <password> as password to atc
    And user click submit to atc
    And user click Add to cart on all product
    Then product verify <status> amount result
    Examples:
      | user_name | password | status |
      | standard_user | secret_sauce | success |