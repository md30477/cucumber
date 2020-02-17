Feature: Free CRM create deals Feature
Scenario: Free CRM create deals Scenario
Given user is on login page
When launch the url 
Then enters username and password
|30477md@gmail.com|Pranuthi2020$|
And click on login 
And verify title in homepage
Then click on create Deals
Then enters mandatory details
|title|probability|commission|
|coal1|10|2|
|oil1|5|3|
|gas1|5|5|
Then close browser