Feature: Login to the application

@login_test
Scenario: Test the response and content of the response
When I request the username and password to the application server
Then I should get a response code 200 and menus