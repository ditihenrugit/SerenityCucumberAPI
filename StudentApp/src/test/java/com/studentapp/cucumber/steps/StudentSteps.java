 package com.studentapp.cucumber.steps;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.HashMap;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.utils.TestUtils;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class StudentSteps {
	static String email = null;
	@Steps
	StudentSerenitySteps serenitySteps;
	
	@When("^User sends a GET request to the list endpoint, user should get a valid status code 200$")
	public void verifyStatusCode200(){
		SerenityRest.rest().given().when().get("/list").then().statusCode(200);
	}

	@When("^I create a new student by providing the information firstname (.*) lastname (.*) email (.*) programme (.*) and courses (.*)$")
	public void createStudent(String firstName, String lastName, String _email, String programme, String courses){
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add(courses);
		email = TestUtils.getRandomValue() + _email;
		serenitySteps.createStudent(firstName, lastName, email, programme, arrayList).assertThat().statusCode(201);
	}
	
	@Then("^I verify student with email (.*) is created$")
	public void verifyCreatedStudent(String emailId){
		HashMap<String, Object> hashMap= serenitySteps.getStudentsInfoByEmail(email);
		assertThat(hashMap , hasValue(email));
	}
	
	String bodyContent = "<User.Login><Username>Admin</Username><Password>Password</Password><UI>CBW</UI></User.Login>";
	HashMap< String, Object> value = new HashMap<String, Object>();
	
	@When("^I request the username and password to the application server$")
	public void sendRequest(){
		ValidatableResponse test=SerenityRest.rest().given().contentType(ContentType.XML).when().body(bodyContent)
				.get("/IDXWFData.asp").then();
		String response = serenitySteps.createResponseLog(test);
		System.out.println("Rest Output :"+response);
	}
	
	@Then("^I should get a response code 200 and menus$")
	public void checkResponse(){
		SerenityRest.rest().given().contentType(ContentType.XML).when().body(bodyContent)
		.get("/IDXWFData.asp").then().statusCode(200);
	}
}