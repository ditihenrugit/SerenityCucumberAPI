Feature: Testing different request in the student app

@verify_status
Scenario: Verifying the user access to the application
When User sends a GET request to the list endpoint, user should get a valid status code 200

@create_multiple_students
Scenario Outline: Creating multiple students
When I create a new student by providing the information firstname <firstName> lastname <lastName> email <email> programme <programme> and courses <courses>
Then I verify student with email <email> is created

Examples:
|firstName|lastName|email|programme|courses|
|diti|gohain|diti@gmail.com|Math|Pure|
|henru|gogoi|henru@gmail.com|Math|Applied|  