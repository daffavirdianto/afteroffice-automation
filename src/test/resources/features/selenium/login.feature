Feature: Login Scenario

  Background: User landend to login page
    Given User is on the login page

  Scenario: login with valid credentials
    When User inputs email "standard_user" and password "secret_sauce"
    Then User should be redirected to the products page

    Scenario Outline: login with invalid credentials
      When User inputs email "<email>" and password "<password>"
      Then Verify error message "<error_message>"

      Examples:
        | email             | password     | error_message                                      |
        | locked_out_user   | secret_sauce | Epic sadface: Sorry, this user has been locked out.|
        |                   | secret_sauce | Epic sadface: Username is required                 |
        | standard_user     |              | Epic sadface: Password is required                 |
