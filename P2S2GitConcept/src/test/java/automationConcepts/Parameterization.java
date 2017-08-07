package automationConcepts;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameterization {
	
	//parameterization
	
	 public void calculateEMI()
	 {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Selenium Docs\\BrowserDrivers\\ChromeDriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://www.deal4loans.com/Contents_Calculators.php");
		
		ExcelRead oExcel = new ExcelRead("C:\\Selenium\\Selenium Docs\\P2S2 Class Notes\\D4LTestData.xls", "EMICalc");
				
		driver.findElement(By.id("Loan_Amount")).clear();
		driver.findElement(By.id("Loan_Amount")).sendKeys(oExcel.getCellData("LoanAmount", 1));
		
		
		driver.findElement(By.name("rate")).clear();
		driver.findElement(By.name("rate")).sendKeys(oExcel.getCellData("IntRate", 1));
		
		driver.findElement(By.name("months")).clear();
		driver.findElement(By.name("months")).sendKeys(oExcel.getCellData("Months", 1));
		
		driver.findElement(By.name("button")).click();
	    
	  
	 }
	
	 @Test	 
	 public void calculateEMIDatDriven() throws InterruptedException
	 {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Selenium Docs\\BrowserDrivers\\ChromeDriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://www.deal4loans.com/Contents_Calculators.php");
		
		ExcelRead oExcel = new ExcelRead("C:\\Selenium\\Selenium Docs\\P2S2 Class Notes\\D4LTestData.xls", "EMICalc");
			
		for(int i=3; i<=oExcel.rowCount(); i++)
		{
			driver.findElement(By.id("Loan_Amount")).clear();
			driver.findElement(By.id("Loan_Amount")).sendKeys(oExcel.getCellData("LoanAmount", i));
			
			
			driver.findElement(By.name("rate")).clear();
			driver.findElement(By.name("rate")).sendKeys(oExcel.getCellData("IntRate", i));
			
			driver.findElement(By.name("months")).clear();
			driver.findElement(By.name("months")).sendKeys(oExcel.getCellData("Months", i));
			
			driver.findElement(By.name("button")).click();
			
			Thread.sleep(3000);
		    
		}
	 }

}
