$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("login.feature");
formatter.feature({
  "line": 1,
  "name": "Free CRM Login Feature",
  "description": "",
  "id": "free-crm-login-feature",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 2,
  "name": "Free CRM Login Test scenario",
  "description": "",
  "id": "free-crm-login-feature;free-crm-login-test-scenario",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "user is already on login page",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "launching the url",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "enters usernameand password",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "click on login button",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "verify home page titile",
  "keyword": "And "
});
formatter.match({
  "location": "LoginStepDefinition.user_is_already_on_login_page()"
});
formatter.result({
  "duration": 15627282900,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.launching_the_url()"
});
formatter.result({
  "duration": 4580425000,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.enters_usernameand_password()"
});
formatter.result({
  "duration": 374969700,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.click_on_login_button()"
});
formatter.result({
  "duration": 3093798700,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.verify_home_page_titile()"
});
formatter.result({
  "duration": 11772500,
  "status": "passed"
});
});