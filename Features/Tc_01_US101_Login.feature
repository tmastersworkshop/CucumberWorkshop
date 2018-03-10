#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Title of your feature
  I want to use this template for my feature file

@Smoketest @Regression	
  Scenario: As per the user story US1790 validate the Login functionality of the OrangeHRM
  
    Given I want to launch the firefox browser
    And i need to maximize it
		When it maximizes enter the url
		Then I need to enter "valid" credentials
		
	@Regression	
		Scenario: As per the user story US1790 validate some functionality of the OrangeHRM
  
    Given I want to launch the firefox browser
    And i need to maximize it
		When it maximizes enter the url
		Then I need to enter "invalid" credentials
	
		
		