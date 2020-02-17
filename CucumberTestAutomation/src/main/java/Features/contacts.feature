Feature: Free CRM Create New Contact Feature

Scenario Outline: Free CRM Create list of New Contacts
Given user is already on login page
When launching the url 
Then enters "<username>" and "<password>"
And click on login button 
And verify home page titile
Then click on new contact
Then enters "<firstname>" and "<lastname>" and "<position>"
Then close the browser

Examples:

	| username | password | firstname | lastname |position |
	| 30477md@gmail.com |  Pranuthi2020$ | madvi |d | TL|
	| 30477md@gmail.com |  Pranuthi2020$ | bachi |l | AM|
	| 30477md@gmail.com |  Pranuthi2020$ | pottii |doki | TL|