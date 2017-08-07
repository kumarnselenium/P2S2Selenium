package automationConcepts;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Synchronization {

	public void exForImplicitWait() throws InterruptedException
	{
		WebDriver oBrowser;
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Selenium Docs\\BrowserDrivers\\ChromeDriver.exe");
		oBrowser = new ChromeDriver();
		
		oBrowser.get("http://www.behindwoods.com");
			
		oBrowser.manage().window().maximize();
		
		oBrowser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		Thread.sleep(5000);
		
		//Get the Parent Browser/window ID
		String sParentID = oBrowser.getWindowHandle();
		
		//Click on Goto Behindwoods Movies
		oBrowser.findElement(By.xpath("//area[@shape='rect']")).click();
		
		
			
		//All Browser/Window IDs
		Set<String> sAllBrowserIds = oBrowser.getWindowHandles();				
		
		
		//Use the below forloop for the more than 1 Child Browser
		//Switch the focus from Main Window to Child Window by excluding the sParentID from sAllBrowserIds
		for(String sEachBrwID:sAllBrowserIds)
		{
			if(!(sEachBrwID.equals(sParentID)))	//Excluding the Parent browser id
			{
				//Changing the focus to Child browser
				oBrowser.switchTo().window(sEachBrwID);
				break;	//Exiting the For loop
			}
		}	
		
		//Switch the focus from Main Window to Child Window
		for(String sEachBrwID:sAllBrowserIds)
		{
			//Changing the focus to Child browser
			oBrowser.switchTo().window(sEachBrwID);
			
			String sBrwTitle = oBrowser.getTitle();
			
			if(sBrwTitle.contains("Emirates"))	//Excluding the Parent browser id
			{			
				break;	//Exiting the For loop
			}
		}		
	
			
		//Click on the Advertise
		oBrowser.findElement(By.xpath("//a[contains(@href,'advertise')]")).click();
	}

	@Test
	public void exForExplicitWait() throws InterruptedException
	{
		WebDriver oBrowser;
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Selenium Docs\\BrowserDrivers\\ChromeDriver.exe");
		oBrowser = new ChromeDriver();
		
		oBrowser.get("http://www.behindwoods.com");
			
		oBrowser.manage().window().maximize();
		
		oBrowser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		Thread.sleep(5000);
		
		//Get the Parent Browser/window ID
		String sParentID = oBrowser.getWindowHandle();
		
		//Click on Goto Behindwoods Movies
		oBrowser.findElement(By.xpath("//area[@shape='rect']")).click();
		
		//Thread.sleep(180000);
			
		//All Browser/Window IDs
		Set<String> sAllBrowserIds = oBrowser.getWindowHandles();				
		
		
		//Use the below forloop for the more than 1 Child Browser
		//Switch the focus from Main Window to Child Window by excluding the sParentID from sAllBrowserIds
		for(String sEachBrwID:sAllBrowserIds)
		{
			if(!(sEachBrwID.equals(sParentID)))	//Excluding the Parent browser id
			{
				//Changing the focus to Child browser
				oBrowser.switchTo().window(sEachBrwID);
				break;	//Exiting the For loop
			}
		}	
		
		//Switch the focus from Main Window to Child Window
		for(String sEachBrwID:sAllBrowserIds)
		{
			//Changing the focus to Child browser
			oBrowser.switchTo().window(sEachBrwID);
			
			String sBrwTitle = oBrowser.getTitle();
			
			if(sBrwTitle.contains("Emirates"))	//Excluding the Parent browser id
			{			
				break;	//Exiting the For loop
			}
		}		
	
		WebDriverWait oWDW = new WebDriverWait(oBrowser, 180);
		oWDW.until(ExpectedConditions.visibilityOf(oBrowser.findElement(By.xpath("//a[contains(@href,'advertise')]"))));
			
		
		//WebDriverWait oWDW = new WebDriverWait(oBrowser, 180);
		//oWDW.until(ExpectedConditions.visibilityOf(oBrowser.findElement(By.xpath("//div[contains(text(),'file uploaded successfully')]"))));
		
		//Click on the Advertise
		oBrowser.findElement(By.xpath("//a[contains(@href,'advertise')]")).click();
	}

	
}
