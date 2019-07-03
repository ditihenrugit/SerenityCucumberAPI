package com.cbwuirest.utils;

import java.io.InputStream;
import java.util.Properties;

public enum CBWTestProperties {
	INSTANCE;
	public static Properties testProperties;
	
	CBWTestProperties(){
		loadTestproperties();
	}
	
	public static void loadTestproperties(){
		InputStream inputStram=null; 
		try{
			inputStram = CBWTestProperties.class.getResourceAsStream("/properties/usercredentials.properties");
			testProperties = new Properties();
			testProperties.load(inputStram);
		}catch(Throwable th)
		{
			System.out.println("Error loading usercredentials.properties file - "
		              + th.getMessage());
		}finally{
			try{
				inputStram.close();
			}catch(Throwable th){
				System.out.println("Error closing usercredentials.properties file - "
			              + th.getMessage());
			}
		}
	}
	
	public String getTestProperty(String propertyName){
		return getTestProperty(propertyName,"Unknown");
	}
	
	public String getTestProperty(String propertyName,String defaultName){
		if(testProperties==null)
		{
			return "!@#_NOT_LOADED_#@!";
		}
		return testProperties.getProperty(propertyName,defaultName);
	}
}
