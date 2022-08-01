package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	public LandingPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//img[contains(@src,'coinmarketcap_1.svg')]")
	public WebElement img_CoinMarket;
	
	@FindBy(xpath="//div[@class=\"gv-close\"]")
	public WebElement alertClose;
	
	
	@FindBy(xpath="//div[4]/div[2]//div[1]/p[(text()='Show rows')]/../div")
	public WebElement drpShowRows;
	
	
	
	@FindBy(xpath="//button[text()='Cryptocurrencies']")
	public WebElement btnCryptoCurrencies;
		
	@FindBy(xpath="(//p[text()='Show rows'])[1]")
	public WebElement txtShowRows;
	
	
	@FindBy(xpath="(//button[contains(@class,'filter')])[2]")
	public WebElement addFilter;
	
	
	@FindBy(xpath="//button[text()='Algorithm']")
	public WebElement btnAlgorithm;
	
	
	@FindBy(xpath="//*[@class='tippy-content']/div/div[1]//ul/li")
	public List<WebElement> algoElements;

	@FindBy(xpath="//button[text()='1 More Filter']")
	public WebElement btnMoreFilter;
	
	@FindBy(xpath="(//button[text()='All Cryptocurrencies'])[1]")
	public WebElement btnAllCryptocurrencies;
	
	@FindBy(xpath="//button[text()='Coins']")
	public WebElement btnCoins;
	
	@FindBy(xpath="//label[@id='mineable']")
	public WebElement toggleMineable;
	
	@FindBy(xpath="//label[@id='audited']")
	public WebElement toggleAudited;
	
	
	@FindBy(xpath="//button[text()='Tokens']")
	public WebElement btnTokens;

	
	@FindBy(xpath="//button[text()='Price']")
	public WebElement btnPrice;
	
	
	@FindBy(xpath="//input[contains(@data-qa-id,'min')]")
	public WebElement txtMinPrice;
	
	@FindBy(xpath="//input[contains(@data-qa-id,'max')]")
	public WebElement txtMaxPrice;
	
	@FindBy(xpath="//button[text()='Apply Filter']")
	public WebElement btnApplyFilter;
	
	@FindBy(xpath="//button[text()='Show results']")
	public WebElement btnShowResults;
	
	
	
}
