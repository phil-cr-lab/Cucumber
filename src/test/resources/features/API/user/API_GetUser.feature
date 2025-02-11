@API_getUser
Feature: Get a user using the API

  Scenario: Validate getting the information of a user
    Given The user already exists
    When I send the request to get the user information
    Then I get the user information