package stepDefinitions;

import io.cucumber.java.en.*;
import utilities.APIExtensions;


import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import dataProviders.ConfigReader;


public class ApiStepBindings {
	
	ConfigReader objConfigReader;
	APIExtensions objApiExtensions;
	public static String apiKey,apiUrl,apiResult;
	public static BigDecimal price;
	
	@Given("Setup the apikey to convert {string} in {string} to {string}")
	public void setup_the_apikey_to_convert_in_to(String amountToConvert, String sourceCurrency, String destCurrency) {
		objConfigReader=new ConfigReader();
		apiKey = objConfigReader.getApiKey();
		apiUrl=objConfigReader.getApiUrl();
		objApiExtensions=new APIExtensions();
		apiResult=objApiExtensions.apiSetupandExecute(apiKey, apiUrl, amountToConvert, null, sourceCurrency, null, destCurrency);
	}
	
	@When("{string} converted value is retrieved")
	public void converted_value_is_retrieved(String destCurrency) {
		
		
		JSONObject jsonResult = new JSONObject(apiResult);
		JSONObject data=jsonResult.getJSONArray("data").getJSONObject(0);
		JSONObject quote=data.getJSONObject("quote");
		price=quote.getJSONObject("GBP").getBigDecimal("price");
		
		
		
		
	}
	@Then("Convert amount received in destCurrency to {string}")
	public void convert_amount_received_in_dest_currency_to(String destCurrency) throws URISyntaxException, IOException {
		String apiUri="https://pro-api.coinmarketcap.com/v2/cryptocurrency/quotes/latest";
		List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
		 paratmers.add(new BasicNameValuePair("symbol",destCurrency));
		 paratmers.add(new BasicNameValuePair("convert","GBP"));
		
		 
		 
		 String apiresult = APIExtensions.makeAPICall(apiUri, paratmers,apiKey);
		 JSONObject jsonResult = new JSONObject(apiresult);
		 JSONObject data=jsonResult.getJSONObject("data").getJSONArray("DOGE").getJSONObject(0);
		 JSONObject quote=data.getJSONObject("quote");
		 BigDecimal  dogePrice=quote.getJSONObject("GBP").getBigDecimal("price");
		 MathContext mc= new MathContext(2);
		 System.out.println("Number of DOGE Coins that can be purchased are:"+ price.divide(dogePrice,mc).toBigInteger());
		 
	 
	}






}
