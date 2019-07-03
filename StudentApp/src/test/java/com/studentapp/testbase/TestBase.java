package com.studentapp.testbase;

import io.restassured.RestAssured;

import org.junit.BeforeClass;

public class TestBase {
	@BeforeClass
	public static void init()
	{
		//RestAssured.baseURI="http://localhost:8082/student";
		//RestAssured.baseURI = "https://cb70t.am.health.ge.com/cfweb";
		RestAssured.baseURI = "https://cb71t.am.health.ge.com/cfweb/IDXWFData.asp";
		
		
	}

}
