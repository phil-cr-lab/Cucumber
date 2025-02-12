@API_loginUser
Feature: Login a user using the API
#For this feature to work, SignupLoginLogout.feature must have ran before
#This feature stores the token on disk

  Scenario: Validate user logging in
    Given The user already exists
    When I send the request to log in
    Then I receive the existing user information
    And I receive a token
