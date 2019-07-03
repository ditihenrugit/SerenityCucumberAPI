package com.studentapp.utils;

import java.util.concurrent.TimeUnit;


import static org.hamcrest.Matchers.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReusableSpecifications {
	public static RequestSpecBuilder rspec;
	public static RequestSpecification requestSpecification;
	public static ResponseSpecBuilder responseSpec;
	public static ResponseSpecification responseSpecification;
	
	public static RequestSpecification getGenericRequestSpec(){
		rspec = new RequestSpecBuilder();
		rspec.setContentType(ContentType.JSON);
		requestSpecification = rspec.build();
		return requestSpecification;
	}

	public static ResponseSpecification getGenericResponseSpec(){
		responseSpec = new ResponseSpecBuilder();
		responseSpec.expectHeader("Content-Type", "application/json;charset=UTF-8");
		responseSpec.expectHeader("Transfer-Encoding", "chunked");
		responseSpec.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);
		responseSpecification = responseSpec.build();
		return responseSpecification;
	}
}
