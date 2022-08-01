package stepDefinitions;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.*;

import dataProviders.ConfigReader;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

import pageObjManager.POMManager;
import pageObjects.HomePage;
import pageObjects.LandingPage;

import utilities.SeleniumExtensions;

public class StepBindings {
	
	public WebDriver driver;
	POMManager pomManager;
	LandingPage landingPage;
	ConfigReader objConfigReader;
	HomePage homePage;

	SeleniumExtensions seleniumExtensions;
	Map<String, String> beforeColumnValues;
	Map<String, String> afterColumnValues;
	
	@Given("Launch coinmarket url {string}")
	public void launch_coinmarket_url(String url) throws InterruptedException, IOException {
		seleniumExtensions=new SeleniumExtensions();
		objConfigReader=new ConfigReader();
		
		driver=seleniumExtensions.LaunchUrl(objConfigReader.getApplicationUrl());
		 
		pomManager=new POMManager(driver);
		landingPage=pomManager.getLandingPage();
   
        landingPage.alertClose.click();
        Assert.assertTrue(landingPage.img_CoinMarket.isDisplayed());
       
	}
	

	@Given("select {string} from show rows dropdown")
	public void select_from_show_rows_dropdown(String expRowCount) throws InterruptedException {
		
		String actRowCount=landingPage.drpShowRows.getText();
		if(actRowCount!=expRowCount)
		{
		
		landingPage.drpShowRows.click();
		Thread.sleep(2000);
		seleniumExtensions.RowsDropdownSelect(expRowCount);
		Thread.sleep(2000);
		}
		Assert.assertEquals(landingPage.drpShowRows.getText(), expRowCount);
	}

	@Given("capture {string}")
	public void capture(String columnName) throws InterruptedException {
		beforeColumnValues=new Hashtable<>();
		beforeColumnValues=seleniumExtensions.ReadResultsTable(columnName);
		
	}
	
	
	@Given("filter by algorithm {string}")
	public void filter_by_algorithm(String algoValue) throws InterruptedException {
		landingPage.btnCryptoCurrencies.click();
		Thread.sleep(10000);
		//driver.findElement(By.xpath("//button[contains(@class,'filter')])[2]")).click();
		landingPage.addFilter.click();
		Thread.sleep(3000);
		Assert.assertTrue(landingPage.btnAlgorithm.isDisplayed());
		landingPage.btnAlgorithm.click();
		seleniumExtensions.SelectDropdownVal(landingPage.algoElements, algoValue);
		Thread.sleep(4000);
		
	}



	@Given("click Add filter button")
	public void click_add_filter_button() throws InterruptedException {
		
		landingPage.btnMoreFilter.click();
		
		Thread.sleep(5000);
	}


	@Given("select toggle {string}")
	public void select_toggle(String toggleValue) {
		switch(toggleValue)
		{
		case "Mineable":
			landingPage.toggleMineable.click();
			break;
			
		case "Audited":
			landingPage.toggleAudited.click();
			break;
		}
	   
	  
	
	}
	@Given("click All cryptocurrencies and select {string}")
	public void click_all_cryptocurrencies_and_select(String cryptoValue) throws InterruptedException {
		Thread.sleep(4000);
		 landingPage.btnAllCryptocurrencies.click();
		 Thread.sleep(2000);
		 switch(cryptoValue)
			{
			case "Coins":
				landingPage.btnCoins.click();
				break;
				
			case "Tokens":
				landingPage.btnTokens.click();
				break;
			}
		 
		 Thread.sleep(1000);  
	}
	@Given("Select price and set minimum to {string} and maximum {string}")
	public void select_price_and_set_minimum_to_and_maximum(String minValue, String maxValue) throws InterruptedException {
	   landingPage.btnPrice.click();
	   landingPage.txtMinPrice.sendKeys(minValue);
	   landingPage.txtMaxPrice.sendKeys(maxValue);
	   landingPage.btnApplyFilter.click();
	   Thread.sleep(1000);
	   landingPage.btnShowResults.click();
	   Thread.sleep(1000);
	}



	@When("verify page content to match {string}")
	public void verify_page_content_to_match(String columnName) throws InterruptedException {
		afterColumnValues=new Hashtable<>();
		afterColumnValues=seleniumExtensions.ReadResultsTable(columnName);
	}




	@Then("validate the {string} matches for atleast one")
	public void validate_the_matches_for_atleast_one(String colName) {

		Assert.assertTrue("Records in the grid does not match even for one value for the specified column value "+colName,seleniumExtensions.CompareResults(beforeColumnValues, afterColumnValues,colName));
	

	}
	
	@io.cucumber.java.After
	public void Closedriver()
	{
		try
		{
		driver.quit();}
		catch(Exception e)
		{
			//do nothing
		}
	}
	
	@AfterStep
	public void tearDown(Scenario scenario) {
	if(!scenario.getName().toString().contains("api")) {
	            final byte[] screenshot = ((TakesScreenshot) driver)
	                        .getScreenshotAs(OutputType.BYTES);
	            scenario.attach(screenshot, "image/png",scenario.getName().toString()); //stick it in the report
	}
	   
	}
	

}


