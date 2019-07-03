package com.cbwuirest.corelogic;

import io.restassured.http.ContentType;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.serenitybdd.rest.SerenityRest;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class LoginCore {
	public static String sessionId = null;
	public static String loginResponse = null;
	
	
	public String loginBody(String username, String password){
		return "<User.Login><Username>"+username+"</Username><Password>"+password+"</Password><UI>CBWUI</UI></User.Login>";
	}
	
	
	public String getSessionId(String response){
		Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(response)));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		NodeList sessionNodes = doc.getElementsByTagName("Session");
		if (sessionNodes.getLength() > 0) {
			sessionId = sessionNodes.item(0).getTextContent();
		} else {
			// success
		}
		return sessionId;
	}
	
	public String getLoginResponse(String username, String password)
	{
		loginResponse = SerenityRest.rest().given()
				.contentType(ContentType.XML).when()
				.body(loginBody(username, password)).get().asString();
		return loginResponse;
	}
	
	public List<String> getListOfMenu(String response){
		Document doc = null;
		List<String> strList = new ArrayList<String>();
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(response)));
			
			System.out.println("Diti Response:::"+response);
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		NodeList nList = doc.getElementsByTagName("Menu");
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				strList.add(eElement.getElementsByTagName("Name").item(0).getTextContent());
				
				System.out.println("Name : " + eElement.getElementsByTagName("Name").item(0).getTextContent());
			}
		}
		return strList;
	}
}
