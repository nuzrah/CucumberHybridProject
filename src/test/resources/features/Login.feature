#User Story
Feature: Login Functionality

  Background: Open Login Modal
    Given User navigates to login modal

  @regression @smoke
  Scenario Outline: Login with valid credentials
    When User enters valid email address <username>
    And User enters valid password <password> into the password field
    And User clicks on the Sign In button
    Then User is successfully logged in
    Examples:
    |username                |password   |
    |nuzrahn@hsenidmobile.com|applova1234|
    |nuzrahn@applova.io      |applova1234|

  #@dev -> Development not yet done but the testcases have been written
  #@ignore -> Ignore testcase
  #@wip -> Development already done but testcases are being written
  @regression @loginNegativeTests @dev
  Scenario: Login with invalid credentials
    When User enters invalid email address
    And User enters invalid password "applova12345" into the password field
    And User clicks on the Sign In button
    Then User is prompted with a Warning Message related to Consumer not found

  @regression @loginNegativeTests @ignore
  Scenario: Login with valid email and invalid password
    When User enters valid email address nuzrahn@hsenidmobile.com
    And User enters invalid password "applova12345" into the password field
    And User clicks on the Sign In button
    Then User is prompted with a Warning Message related to Authentication Failed

  @regression @loginNegativeTests @wip
  Scenario: Login with invalid email and valid password
    When User enters invalid email address
    And User enters valid password "applova1234" into the password field
    And User clicks on the Sign In button
    Then User is prompted with a Warning Message related to Consumer not found

  @regression @smoke @loginNegativeTests
  Scenario: Login with empty credentials
    When User does not enter email into email field
    And User does not enter password into password field
    And User clicks on the Sign In button
    Then User is prompted with a Warning Message related to Required Fields being empty
    #But User should not be able to Login

