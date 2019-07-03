package com.cbwuirest.helper;


import java.util.List;

import com.cbwuirest.corelogic.LoginCore;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

@SuppressWarnings("serial")
public class LoginHelper extends ScenarioSteps {
	LoginCore loginCore = new LoginCore();
	static String sessionId = null;
	static String response = null;

	@Step
	public void loginToApplication(String username, String password) {
		response = loginCore.getLoginResponse(username, password);
		sessionId = loginCore.getSessionId(response);
	}
	
	@Step
	public boolean verifyMenus(List<String> strList)
	{
		List<String> str=loginCore.getListOfMenu(response);
		System.out.println("Ditimoni "+str);
		return true;
	}
}
