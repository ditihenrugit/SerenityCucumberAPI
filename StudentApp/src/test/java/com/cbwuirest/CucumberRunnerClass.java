package com.cbwuirest;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import org.junit.runner.RunWith;

import com.studentapp.testbase.TestBase;

import cucumber.api.CucumberOptions;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features="src/test/resources/features/",
		//features = "classpath:features",
		tags="@login_hospitaluser"
		
		)
public class CucumberRunnerClass extends TestBase{
	

}
