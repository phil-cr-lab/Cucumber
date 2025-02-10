@API_addUser
Feature: Add a user using the API

  Scenario: Validate Addition of new user
    Given The user to add is new
    When I send the request to add a new user
    Then The new user is added