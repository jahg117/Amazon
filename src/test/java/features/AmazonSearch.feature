Feature: SearchProducts

  Scenario: Validate number of products
    Given I am in Amazon web site
    When I search "pantallas" in amazon
    Then the product section is displayed
    And I can see the number of products displayed