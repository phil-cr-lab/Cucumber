@API_getUser
Feature: Get a user using the API
#For this feature to work, API_LoginUser.feature must have ran before

  Scenario: Validate getting the information of a user
    Given The user already exists
    And I have a token
    When I send the request to get the user information
    Then I receive the user information
    And I receive a token