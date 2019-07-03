package com.cbwuirest.steps;

import java.util.ArrayList;
import java.util.List;

import com.cbwuirest.helper.LoginHelper;
import com.cbwuirest.utils.CBWConstants;
import com.cbwuirest.utils.CBWTestProperties;

import net.thucydides.core.annotations.Steps;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps {
	String username;
	String password;
	String response;
	@Steps
	LoginHelper loginHelper;
	
	@Given("^I login to the application as Admin$")
	public void loginToAppAsAdin(){
		username=CBWTestProperties.INSTANCE.getTestProperty(CBWConstants.ADMINUSERNAME);
		password=CBWTestProperties.INSTANCE.getTestProperty(CBWConstants.ADMINPASSWORD);
		loginHelper.loginToApplication(username, password);
	}
	
	@Given("^I login to the application as Hospital user$")
	public void loginToAppAsHospitalUser(){
		username=CBWTestProperties.INSTANCE.getTestProperty(CBWConstants.HOSPITALUSERNAME);
		password=CBWTestProperties.INSTANCE.getTestProperty(CBWConstants.HOSPITALPASSWORD);
		loginHelper.loginToApplication(username, password);
	}
	
	@When("^I note down the menus from the response$")
	public void noteResponseMenu(){
		
	}
	
	@Then("^I should see the menus listed below:$")
	public void verifyMenus(DataTable data){
		List<List<String>> listData=data.raw();
		List<String> listOfMenus = new ArrayList<String>();
		for(int i=1;i<listData.size();i++){
			listOfMenus.add(listData.get(i).get(0));
		}
		loginHelper.verifyMenus(listOfMenus);
				
	}

}
