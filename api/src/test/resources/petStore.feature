Feature: https://petstore.swagger.io

  Scenario: Retrieve all available pets and confirm that the name doggie with category id 12 is on the list
    Given I set the findbystatus method
    When I get the pet list with status "available"
    Then Verify that category 12 and name "doggie" exists
    
  Scenario: Add a new pet with an auto generated name and status available - Confirm the new pet has been added
  	Given I have a pet with a random name and status "available"
  	When I post to api
  	Then Verify pet was added

  Scenario: From point 2 above retrieve the created pet using the ID
  	Given I have a petid
  	When I get by petid
  	Then Verify my pet has been returned