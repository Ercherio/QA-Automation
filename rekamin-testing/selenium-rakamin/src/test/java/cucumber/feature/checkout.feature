Feature: checkout functionality

  Scenario Outline: Ensure checkout functionality
    Given user have to login first to checkout
    When user input <user_name> as user_name to checkout
    And user input <password> as password to checkout
    And user click submit to checkout
    And user click Add to cart on all product to checkout
    And user click cart button to checkout
    And user click checkout button
    And user input <first_name> as first_name
    And user input <last_name> as last_name
    And user input <postal_code> as postal_code
    And user click continue button
    And user click finish button
    Then user verify <status> checkout result
    Examples:
      | user_name | password | first_name | last_name | postal_code | status |
      | standard_user | secret_sauce | Ercherio | Marpaung | 11470 | success |