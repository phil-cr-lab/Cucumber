@User
Feature: UI and API User functionalities (Contact List App : thinking-tester-contact-list.herokuapp.com)

  @UI @AddUser @Logout @Signup @Prereq
  #This scenario stores the created user information on disk (for training purposes)
  Scenario: Create a new user using the UI (which logs it in) and log out
    Given The user to add is new
    And I access the Signup Page
    When I enter a first name
    And I enter a last name
    And I enter an email address
    And I enter a password
    And I click the submit button
    Then I see the contact list page
    When I click the logout button
    Then I see the login page

  @UI @Login @Logout
  #This scenario gets the user login credentials from the static variable used during the previous scenario
  Scenario: Login the new user using the UI and log out (1)
    Given I access the user Login Page
    When I enter the email address of the created user
    And I enter the password of the created user
    And I click the login button
    Then I see the contact list page
    When I click the logout button
    Then I see the login page

  @UI @Login @Logout
  #This scenario retrieves the user login credentials from disk (Scenario: Create a new user using the UI)
  Scenario: Login the new user using the UI and log out (2)
    Given The user already exists
    And I access the user Login Page
    When I enter an existing account email address
    And I enter its associated password
    And I click the login button
    Then I see the contact list page
    When I click the logout button
    Then I see the login page

  @API @Login @GetToken @Prereq
  #This scenario retrieves the user login credentials from disk (Scenario: Create a new user using the UI)
  #This scenario stores the user token on disk (for training purposes)
  Scenario: Login the new user using the API
    Given The user already exists
    When I send the request to log in
    Then I receive the existing user information
    And I receive a token

  @API @GetUser
  #This scenario retrieves the user token from disk (from Scenario: Login the new user using the API)
  Scenario: Get the new user information using the API
    Given I have a token
    When I send the request to get the user information
    Then I receive the existing user information

  @API @AddUser
  #This scenario gets the user token from the static variable
  Scenario: Create a new user using the API
    Given The user to add is new
    When I send the request to add a new user
    Then I receive the new user information
    And I receive a token