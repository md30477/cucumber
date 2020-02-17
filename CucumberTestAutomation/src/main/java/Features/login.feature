@cucumberHook
Feature: Free CRM Login Feature
Scenario: Free CRM Login Test scenario

Given user is already on login page
When launching the url 
Then enters "30477md@gmail.com" and "Pranuthi2020$"
And click on login button 
And verify home page titile
Then close the browser

Scenario Outline: Free CRM Login Test scenario with multipletestdata
Given user is already on login page
When launching the url 
Then enters "<username>" and "<password>"
And click on login button 
And verify home page titile
Then close the browser

Examples:

	| username | password |
	| 30477md@gmail.com |  Pranuthi2020$ |
	| 30477md1@gmail.com |  Pranuthi2020$ |
	


