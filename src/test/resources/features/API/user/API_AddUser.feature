@API_addUser
Feature: Add a user using the API

  Scenario: Validate Addition of new user

    Given The user to add is new

      #The token must have been retrieved when logging in an existing user (API_LoginUser.feature)
    And I have a token

    When I send the request to add a new user
    Then I get a response with the new user information
    And I get a new token