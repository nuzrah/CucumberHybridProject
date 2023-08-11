Feature: Register Functionality

  @regression
  Scenario: User creates an account with only mandatory fields
    Given User navigates to the Register Modal
    When User enters the details into register mandatory fields
      |firstName  |Nuzrah                |
      |birthYear  |1997                  |
      |birthMonth |4                     |
      |birthDate  |10                    |
      |phoneNumber|2812363378            |
      |password   |applova1234           |
    And User clicks on Sign Up button
    Then User account gets created successfully

  @regression @smoke
  Scenario: User creates an account without filling any details
    Given User navigates to the Register Modal
    When User does not enter any details into any fields
    And User clicks on Sign Up button
    Then User is prompted with a warning message related to Required Fields being empty