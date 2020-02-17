$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/main/java/Features/login.feature");
formatter.feature({
  "line": 2,
  "name": "Free CRM Login Feature",
  "description": "",
  "id": "free-crm-login-feature",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@cucumberHook"
    }
  ]
});
formatter.before({
  "duration": 19194552000,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Free CRM Login Test scenario",
  "description": "",
  "id": "free-crm-login-feature;free-crm-login-test-scenario",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "user is already on login page",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "launching the url",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "enters \"30477md@gmail.com\" and \"Pranuthi2020$\"",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "click on login button",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "verify home page titile",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "close the browser",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginStepDefinition.user_is_already_on_login_page()"
});
formatter.result({
  "duration": 159983800,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.launching_the_url()"
});
formatter.result({
  "duration": 76500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "30477md@gmail.com",
      "offset": 8
    },
    {
      "val": "Pranuthi2020$",
      "offset": 32
    }
  ],
  "location": "LoginStepDefinition.enters_usernameand_password(String,String)"
});
formatter.result({
  "duration": 1036819900,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.click_on_login_button()"
});
formatter.result({
  "duration": 2999982000,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.verify_home_page_titile()"
});
formatter.result({
  "duration": 11627100,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.close_the_browser()"
});
formatter.result({
  "duration": 1279225000,
  "status": "passed"
});
formatter.after({
  "duration": 260800,
  "status": "passed"
});
formatter.scenarioOutline({
  "line": 12,
  "name": "Free CRM Login Test scenario with multipletestdata",
  "description": "",
  "id": "free-crm-login-feature;free-crm-login-test-scenario-with-multipletestdata",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 13,
  "name": "user is already on login page",
  "keyword": "Given "
});
formatter.step({
  "line": 14,
  "name": "launching the url",
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "enters \"\u003cusername\u003e\" and \"\u003cpassword\u003e\"",
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "click on login button",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "verify home page titile",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "close the browser",
  "keyword": "Then "
});
formatter.examples({
  "line": 20,
  "name": "",
  "description": "",
  "id": "free-crm-login-feature;free-crm-login-test-scenario-with-multipletestdata;",
  "rows": [
    {
      "cells": [
        "username",
        "password"
      ],
      "line": 22,
      "id": "free-crm-login-feature;free-crm-login-test-scenario-with-multipletestdata;;1"
    },
    {
      "cells": [
        "30477md@gmail.com",
        "Pranuthi2020$"
      ],
      "line": 23,
      "id": "free-crm-login-feature;free-crm-login-test-scenario-with-multipletestdata;;2"
    },
    {
      "cells": [
        "30477md1@gmail.com",
        "Pranuthi2020$"
      ],
      "line": 24,
      "id": "free-crm-login-feature;free-crm-login-test-scenario-with-multipletestdata;;3"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 16784820100,
  "status": "passed"
});
formatter.scenario({
  "line": 23,
  "name": "Free CRM Login Test scenario with multipletestdata",
  "description": "",
  "id": "free-crm-login-feature;free-crm-login-test-scenario-with-multipletestdata;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@cucumberHook"
    }
  ]
});
formatter.step({
  "line": 13,
  "name": "user is already on login page",
  "keyword": "Given "
});
formatter.step({
  "line": 14,
  "name": "launching the url",
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "enters \"30477md@gmail.com\" and \"Pranuthi2020$\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "click on login button",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "verify home page titile",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "close the browser",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginStepDefinition.user_is_already_on_login_page()"
});
formatter.result({
  "duration": 622600,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.launching_the_url()"
});
formatter.result({
  "duration": 62100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "30477md@gmail.com",
      "offset": 8
    },
    {
      "val": "Pranuthi2020$",
      "offset": 32
    }
  ],
  "location": "LoginStepDefinition.enters_usernameand_password(String,String)"
});
formatter.result({
  "duration": 898986500,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.click_on_login_button()"
});
formatter.result({
  "duration": 2999072100,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.verify_home_page_titile()"
});
formatter.result({
  "duration": 25617500,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.close_the_browser()"
});
formatter.result({
  "duration": 1315298200,
  "status": "passed"
});
formatter.after({
  "duration": 58100,
  "status": "passed"
});
formatter.before({
  "duration": 16852899500,
  "status": "passed"
});
formatter.scenario({
  "line": 24,
  "name": "Free CRM Login Test scenario with multipletestdata",
  "description": "",
  "id": "free-crm-login-feature;free-crm-login-test-scenario-with-multipletestdata;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@cucumberHook"
    }
  ]
});
formatter.step({
  "line": 13,
  "name": "user is already on login page",
  "keyword": "Given "
});
formatter.step({
  "line": 14,
  "name": "launching the url",
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "enters \"30477md1@gmail.com\" and \"Pranuthi2020$\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "click on login button",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "verify home page titile",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "close the browser",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginStepDefinition.user_is_already_on_login_page()"
});
formatter.result({
  "duration": 674100,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.launching_the_url()"
});
formatter.result({
  "duration": 159900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "30477md1@gmail.com",
      "offset": 8
    },
    {
      "val": "Pranuthi2020$",
      "offset": 33
    }
  ],
  "location": "LoginStepDefinition.enters_usernameand_password(String,String)"
});
formatter.result({
  "duration": 959803000,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.click_on_login_button()"
});
formatter.result({
  "duration": 3000871500,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.verify_home_page_titile()"
});
formatter.result({
  "duration": 11848100,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.close_the_browser()"
});
formatter.result({
  "duration": 1219828100,
  "status": "passed"
});
formatter.after({
  "duration": 74300,
  "status": "passed"
});
});