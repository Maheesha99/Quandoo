@Regression @Smoke @Restaurants
Feature:Restaurants

  Background: user landed on the home page and accept the location enable alert
    #Executes before each and every scenario
    Given user accept the device location Alert if available
    And user landed on to the home page and verify "Discovery" label


  Scenario Outline: TC_01_Verify User signup and navigate to review section of a selected restaurant
    #signup - Note: change the email address to a unique one before executing the script
    Then user click on Hamburger menu
    Then user click on Log in or Sign up option
    Then user enters the given details "<firstName>","<lastName>","<emailAddress>","<password>"
    Then user agrees to Terms and conditions
    Then user click on Create Account button
    And user landed on to the home page and verify "Discovery" label
    #search a restaurant and apply a simple filter
    Then user search a "<cuisine>" restaurant in "<location>"
    Then user filter "<filter>" from the result list
    #Apply advance filter by selecting number of people,Date and Time
    Then user do an advance filter by having "<numberOfPeople>","<date>","<time>"
    #Select a restaurant and navigate to review section
    Then user select "<itemNumber>" from the results list
    Then user navigate to review section of the restaurant
    #Post script : user logout from the application
    Then user go back from the restaurant details view
    Then user go back from the advance search view
    Then user go back from the simple search view
    Then user click on Hamburger menu
    Then user logout from the application

    Examples:
      | firstName | lastName | emailAddress                | password    | cuisine | location | itemNumber | filter   | numberOfPeople | date    | time  |
      | Maheesha  | Lunuwila | mahishalunuwila15@gmail.com | test1234567 | Italian | Berlin   | 2          | Open Now | 4              | nextday | 17:00 |