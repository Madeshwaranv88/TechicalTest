package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import dataProviders.ConfigReader;

public class HomePage {
	
	

	WebDriver driver;
	dataProviders.ConfigReader objConfigReader;
	
	public HomePage(WebDriver driver) throws IOException{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		objConfigReader= new ConfigReader();
	}
	
		
	public String ReturnUrl() {
		return objConfigReader.getApplicationUrl();
	}
	
}
