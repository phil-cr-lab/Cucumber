@signup
Feature: Contact List App - Create a user, log it in, log it out
#This feature stores the created user information on disk in Json format

  Scenario: Validate Creation of new user
    Given The user to add is new
    And I access the Signup Page
    When I enter a first name
    And I enter a last name
    And I enter an email address
    And I enter a password
    And I click the submit button
    Then I see the contact list page

  Scenario: Validate Login of newly created user
    Given I access the user Login Page
    When I enter the email address of the created user
    And I enter the password of the created user
    And I click the login button
    Then I see the contact list page

  Scenario: Validate Logout of newly created user
    Given I access the user Login Page
    And I enter the email address of the created user
    And I enter the password of the created user
    And I click the login button
    When I click the logout button
    Then I see the login page
