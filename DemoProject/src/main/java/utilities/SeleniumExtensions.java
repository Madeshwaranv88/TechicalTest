package utilities;

import java.io.IOException;
import java.time.Duration;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;



import org.openqa.selenium.By;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import pageObjManager.POMManager;
import pageObjects.HomePage;

public class SeleniumExtensions {
	public WebDriver driver;
	POMManager objPOMManager;
	HomePage objHomePage;
	
	public WebDriver LaunchUrl(String url) throws IOException
	{
	
		System.setProperty("webdriver.edge.driver", "C:\\Selenium\\edgedriver\\msedgedriver.exe");
	    org.openqa.selenium.edge.EdgeDriverService service = org.openqa.selenium.edge.EdgeDriverService.createDefaultService();
	    EdgeOptions options=new EdgeOptions();
	    driver=new EdgeDriver(service,options);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    driver.manage().window().maximize();
	    driver.get(url);
	   
	    return driver;
	}
	
	public void RowsDropdownSelect(String rowCount)
	{
		driver.findElement(By.xpath("//*[@id='tippy-1']//button[(text()="+rowCount+")]")).click();
	}
	
	public void SelectDropdownVal(List<WebElement> drpdownElems,String option)
	{
	try
	{
		  for (WebElement opt : drpdownElems) {
		    if (opt.getText().equals(option)) {
		    	opt.click();
		    }
		
	}}catch(StaleElementReferenceException e) {
		
		//do nothing
	}
	}
	
	public Map<String, String> ReadResultsTable(String columnName) throws InterruptedException
	{
		Map<String, String> tempValues = new HashMap<>();
	
		int rowCount=driver.findElements(By.xpath("//table[contains(@class,'cmc-table')]//tr")).size();
		int colCount=driver.findElements(By.xpath("//table[contains(@class,'cmc-table')]//tr[1]/th")).size();
		
			
		for(int j=2; j<colCount; j++){
			String colName=driver.findElement(By.xpath("//table[contains(@class,'cmc-table')]//tr[1]/th["+j+"]")).getText().trim().replace("\r\n", "");
			if(colName.equals(columnName))
			{
				for(int i=1; i<rowCount; i++){
				
					String key=driver.findElement(By.xpath("(//table[contains(@class,'cmc-table')]//tr["+i+"]/td[3])")).getText().replace("\n", " ");
					String value=driver.findElement(By.xpath("(//table[contains(@class,'cmc-table')]//tr["+i+"]/td["+j+"])")).getText().replace("\n", " ");
				try
				{
					tempValues.put(key,value );
				}
				catch(NoSuchElementException e)
				{
					tempValues.put(key,value );
				}
				    Thread.sleep(500);
				}
				break;
			}
			}
		return tempValues;
	}
	
	public Map<String, String> ReadResultsTable() throws InterruptedException
	{
		Map<String, String> tempValues = new HashMap<>();
	//	List<String> tempValues=new ArrayList<String>();
		int rowCount=driver.findElements(By.xpath("//table[contains(@class,'cmc-table')]//tr")).size();
		int colCount=driver.findElements(By.xpath("//table[contains(@class,'cmc-table')]//tr[1]/th")).size();
		
		for(int i=1; i<rowCount; i++){	
		String keyName=driver.findElement(By.xpath("(//table[contains(@class,'cmc-table')]//tr["+i+"]/td[3]//p)[1]")).getText();
		StringBuilder keyValue=new StringBuilder();
		colCount=6;
		for(int j=2; j<colCount-1; j++){
			try {
				keyValue.append(driver.findElement(By.xpath("//table[contains(@class,'cmc-table')]//tr["+i+"]/td["+j+"]//span")).getText());
			}catch(Exception e)
			{
				keyValue.append(driver.findElement(By.xpath("(//table[contains(@class,'cmc-table')]//tr["+i+"]/td["+j+"]//p)[1]")).getText());
			}
			if(j!=colCount-1)
			{
			keyValue.append(";");}
		//Thread.sleep(500);
		}
		tempValues.put(keyName,keyValue.toString());
		}
		return tempValues;
	}
	
	public boolean CompareResults(Map<String, String> sourceDict,Map<String, String> destDict,String colName)
	{
		boolean matchFound=false;
		 
		 for (Map.Entry<String, String> tempVal : destDict.entrySet()) {
			  String destKey = tempVal.getKey();
			  String destVal = tempVal.getValue();
			  String sourceVal = sourceDict.get(destKey); 
			  Float sourceFLtVal=0.00f,destFltVal=0.00f;
			if(sourceVal!=null)
			{
				switch(colName)
				{
				case "Price":
					sourceFLtVal=Float.parseFloat(sourceVal.substring(1, sourceVal.length()).replace(",",""));
					destFltVal=Float.parseFloat(destVal.substring(1, destVal.length()).replace(",",""));
					break;
					
				}
				if( (sourceFLtVal-destFltVal)<0.6)
				{
					System.out.println("Destination value of "+destKey+" is "+destVal+" , has a exact/close match with source value "+sourceVal);
					matchFound=true;
					
				}
				else
				{
					System.out.println("Destination value of "+destKey+" is "+destVal+" , is a not a close match with source value "+sourceVal);
				}
			}
			else
			{
				System.out.println("Source value of "+destKey+" is not found in destination");
			}
			 
			 
			}
		return (matchFound);
		
	}
	
	
}
