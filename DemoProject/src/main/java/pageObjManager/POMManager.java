package pageObjManager;


import org.openqa.selenium.WebDriver;
import pageObjects.LandingPage;



public class POMManager {
	
	public WebDriver driver;
	private LandingPage landingPage;

	
	public POMManager(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public LandingPage getLandingPage(){

		return (landingPage == null) ? landingPage = new LandingPage(driver) : landingPage;

	}
	

	
	


}
