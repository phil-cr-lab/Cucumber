@login
Feature: login new user

   Scenario: Validate Login of newly created user
     Given I access the Login Page
     And I enter an existing account email address
     And I enter its associated password
     And I click the login button
     Then I see the contact list page