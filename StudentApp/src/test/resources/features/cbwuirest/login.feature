@login
Feature: Login to the application

  @login_admin
  Scenario: Login to cbwui as admin
    Given I login to the application as Admin
    
  @login_hospitaluser
  Scenario: Verify the menu items
    Given I login to the application as Hospital user
    When I note down the menus from the response
    Then I should see the menus listed below:
    |Menus|
    |Bed Manager|
    |Front Desk|
    |Pre-Arrive|
    |Pre-Billing|
    |Billing|
    |Scheduler|
    |ETM|
    |ETM Operations|
