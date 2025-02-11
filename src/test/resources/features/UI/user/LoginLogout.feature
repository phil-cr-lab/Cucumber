@login
Feature: login existing user
#For this feature to work, SignupLoginLogout.feature must have ran before

   Scenario: Validate Login of an existing user
     Given The user already exists
     And I access the user Login Page
     When I enter an existing account email address
     And I enter its associated password
     And I click the login button
     Then I see the contact list page