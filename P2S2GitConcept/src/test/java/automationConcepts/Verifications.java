package automationConcepts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Verifications {

	 public void calculateEMITraditionalVerification() throws InterruptedException
	 {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Selenium Docs\\BrowserDrivers\\ChromeDriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://www.deal4loans.com/Contents_Calculators.php");
		
		//1. Verify EMI Calculator Page displayed
		try{
			if(driver.findElement(By.xpath("//h1[contains(.,'EMI Calculator')]")).isDisplayed())
			{
				System.out.println("EMI calculator page is displayed");
			}
		}
		catch(Exception e)
		{
			System.out.println("EMI calculator page is not displayed");
		}
		
		if(driver.findElements(By.xpath("//h1[contains(.,'EMI Calculator')]")).size()>0)
		{
			System.out.println("EMI calculator page is displayed");
		}
		else
		{
			System.out.println("EMI calculator page is not displayed");
		}
		
		ExcelRead oExcel = new ExcelRead("C:\\Selenium\\Selenium Docs\\P2S2 Class Notes\\D4LTestData.xls", "EMICalc");
				
		driver.findElement(By.id("Loan_Amount")).clear();
		driver.findElement(By.id("Loan_Amount")).sendKeys(oExcel.getCellData("LoanAmount", 1));
		
		driver.findElement(By.name("rate")).click();
		
		String sActualLAText = driver.findElement(By.id("wordloanAmount")).getText();
		String sExpectedLAText = oExcel.getCellData("ExpectedLAText", 1);
		
		if(sActualLAText.equalsIgnoreCase(sExpectedLAText))
		{
			System.out.println("Expected Loan Amount text displayed");
		}
		else
		{
			System.out.println("Expected Loan Amount text not displayed");
		}
		
		driver.findElement(By.name("rate")).clear();
		driver.findElement(By.name("rate")).sendKeys(oExcel.getCellData("IntRate", 1));
		
		driver.findElement(By.name("months")).clear();
		driver.findElement(By.name("months")).sendKeys(oExcel.getCellData("Months", 1));
		
		driver.findElement(By.name("button")).click();	
		
		String sActualEMI = driver.findElement(By.name("pay")).getAttribute("value");
		String sExpectedEMI =  oExcel.getCellData("ExpectedEMI", 1);
		
		if(sActualEMI.equalsIgnoreCase(sExpectedEMI))
		{
			System.out.println("Expected EMI is displayed");
		}
		else
		{
			System.out.println("Expected EMI is not displayed");
		}
		
	  
	 }

	 @Test
	 public void calculateEMIJUnitAssertions() throws InterruptedException, FileNotFoundException
	 {
		 
		//1. Create a notepad/file
		File oFile = new File("C:\\Selenium\\Selenium Docs\\P2S2 Class Notes\\Results.txt");
		
		//2.helps in writing to a file
		PrintWriter oPW = new PrintWriter(oFile);
		
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Selenium Docs\\BrowserDrivers\\ChromeDriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://www.deal4loans.com/Contents_Calculators.php");
		
		//1. Verify EMI Calculator Page displayed
		Assert.assertTrue("EMI calculator page is not displayed", driver.findElement(By.xpath("//h1[contains(.,'EMI Calculator')]")).isDisplayed());
		System.out.println("EMI calculator page is displayed");
		oPW.println("EMI calculator page is displayed");
		
		Assert.assertTrue("EMI calculator page is not displayed", driver.findElements(By.xpath("//h1[contains(.,'EMI Calculator')]")).size()>0);
		System.out.println("EMI calculator page is displayed");
		oPW.println("EMI calculator page is displayed");
		
		ExcelRead oExcel = new ExcelRead("C:\\Selenium\\Selenium Docs\\P2S2 Class Notes\\D4LTestData.xls", "EMICalc");
				
		driver.findElement(By.id("Loan_Amount")).clear();
		driver.findElement(By.id("Loan_Amount")).sendKeys(oExcel.getCellData("LoanAmount", 1));
		
		driver.findElement(By.name("rate")).click();
		
		String sActualLAText = driver.findElement(By.id("wordloanAmount")).getText();
		String sExpectedLAText = oExcel.getCellData("ExpectedLAText", 1);
		
		Assert.assertTrue("Expected Loan Amount text not displayed", sActualLAText.equalsIgnoreCase(sExpectedLAText));
		System.out.println("Expected Loan Amount text displayed");
		oPW.println("Expected Loan Amount text displayed");
		
		driver.findElement(By.name("rate")).clear();
		driver.findElement(By.name("rate")).sendKeys(oExcel.getCellData("IntRate", 1));
		
		driver.findElement(By.name("months")).clear();
		driver.findElement(By.name("months")).sendKeys(oExcel.getCellData("Months", 1));
		
		driver.findElement(By.name("button")).click();	
		
		String sActualEMI = driver.findElement(By.name("pay")).getAttribute("value");
		String sExpectedEMI =  oExcel.getCellData("ExpectedEMI", 1);
				
		Assert.assertTrue("Expected EMI is not displayed", sActualEMI.equalsIgnoreCase(sExpectedEMI));
		System.out.println("Expected EMI is displayed");
		oPW.println("Expected EMI is displayed");
		
		//Save the file
		oPW.flush();
		
		//Close the file
		oPW.close();
		

	 }

}








