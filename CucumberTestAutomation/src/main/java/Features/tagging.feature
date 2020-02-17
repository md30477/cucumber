Feature: Tagging functionality for Free CRM

@smoketest
Scenario: login to application  as valid user
Given user login to application

@RegressionTest
Scenario: login to application  as invalid user
Given  user login to application

@smoketest @RegressionTest
Scenario: login to application  create contact
Given  user click on contacts

 @RegressionTest
Scenario: login to application  create deal
Given  user click on deal

@E2ETest
Scenario: login to application  create task
Given  user click on task

@RegressionTest
Scenario: generate reports
Given  user verifying reports

@E2ETest
Scenario: application logout
Given  user click on logout

Scenario: close driver
Given  user Scenario: application logout
Given  user click on logout