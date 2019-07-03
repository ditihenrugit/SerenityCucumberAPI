package com.studentapp.cucumber.serenity;

import java.util.HashMap;
import java.util.List;





import java.util.Map;

import com.studentapp.model.StudentClass;
import com.studentapp.utils.ReusableSpecifications;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import io.restassured.http.Headers;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class StudentSerenitySteps {
	StudentClass student = new StudentClass();
	@Step("Creating a student with firstname:{0} , lastName:{1} , email:{2}, programme:{3}, courses:{4}")
	public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme,List<String> courses){
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		return SerenityRest.rest().given().spec(ReusableSpecifications.getGenericRequestSpec())
				.when().body(student).post().then();
	}
	
	@Step("Getting the student information with firstName:{0}")
	public HashMap<String, Object> getStudentsInfoByFirstName(String firstName){
		return SerenityRest.rest().given().when().get("/list")
				.then().log().all().statusCode(200)
				.extract().path("findAll{it.firstName=='"+firstName+"'}.get(0)");
	}
	
	@Step("Updating student informationn with studentId:{0}, firstName:{1}, lastName:{2}, email:{3}, programme:{4}, courses:{5}")
	public ValidatableResponse updateStudentInformation(int studentId, String firstName, String lastName, String email, String programme,List<String> courses){
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		return SerenityRest.rest().given().spec(ReusableSpecifications.getGenericRequestSpec()).when().body(student)
		.put("/"+studentId).then();
	}
	
	@Step("Delete the student with studentId: {0}")
	public void deleteStudent(int studentId){
		SerenityRest.rest().given().when().delete("/"+studentId);
	}
	
	@Step("Getting information of the student with id:{0}")
	public ValidatableResponse getResponseById(int studentId){
		return SerenityRest.rest().given().when().get("/"+studentId).then();
	}
	
	@Step("Getting the student information with email:{0}")
	public HashMap<String, Object> getStudentsInfoByEmail(String email){
		return SerenityRest.rest().given().when().get("/list")
				.then().log().all().statusCode(200)
				.extract().path("findAll{it.email=='"+email+"'}.get(0)");
	}
	
	@Step("Get the response log")
	public String createResponseLog(ValidatableResponse response){
	    ExtractableResponse<Response> extractableResponse = response.extract();

	    /*StringBuilder builder = new StringBuilder();
	    builder.append("RESPONSE: ");
	    builder.append(extractableResponse.statusCode());
	    builder.append(" - Headers: ");

	    Headers headers = extractableResponse.headers();
	    Map<String, String> headerMap = new HashMap<>();

	    if (headers != null) {
	        headers.forEach((header) -> headerMap.put(header.getName(), header.getValue()));
	    }

	    builder.append(headerMap);
	    //builder.append(NEW_LINE);
	    builder.append(extractableResponse.body().asString());*/
	   // return builder.toString();
	    
	    return extractableResponse.body().asString();
	}
	}
