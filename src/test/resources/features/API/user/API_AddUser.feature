@API_addUser
Feature: Add a user using the API
#For this feature to work, API_LoginUser.feature must have ran before

  Scenario: Validate Addition of new user
    Given The user to add is new
    And I have a token
    When I send the request to add a new user
    Then I receive the user information
    And I receive a token