Feature: As a User, I want to verify Search and Navigate Feature.
   
	Scenario Outline: Verify the Search result on Webpage.
		Given I am on HomePage
    When I searched for "atta"
    Then Search was successful
    And All links were found working
        
    Examples:
    | atta   |
    | atta   | 

	Scenario Outline: Verify the Search result on Webpage.
		Given I am on HomePage
    When I searched for "sugar"
    Then Search was successful
    And All links were found working
        
    Examples:
    | sugar   |
    | sugar   | 
    
  Scenario Outline: Verify the Search result on Webpage.
  	Given I am on HomePage
    When I searched for "tomato"
    Then Search was successful
    And All links were found working
    
    Examples:
    | tomato   |
    | tomato   | 
    
  Scenario Outline: Verify invalid keyword
  	Given I am on HomePage
    When I searched for "xyz"
    Then Search failed
        
    Examples:
    | xyz   |
    | xyz   | 

  Scenario Outline: Verify invalid keyword
  	Given I am on HomePage
    When I searched for "rwhlka"
    Then Search failed
        
    Examples:
    | rwhlka   |
    | rwhlka   | 
