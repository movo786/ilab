Feature: https://dog.ceo/dog-api/

  Scenario: Verify that a successful message is returned when a user searches for random breeds
    Given I set the random search method
    When I get a random breed
    Then I get a sucessful response
  
  Scenario: Verify that bulldog is on the list of all breeds
  	Given I set the list all breeds method
  	When I get all breeds
  	Then Verify list of all breeds contains "bulldog"
  	
  Scenario: Retrieve all sub-breeds for bulldogs and their respective images
  	Given I set the sub breed method as "bulldog"
  	When I get all sub breeds
  	Then Log all sub breeds and images for "bulldog"