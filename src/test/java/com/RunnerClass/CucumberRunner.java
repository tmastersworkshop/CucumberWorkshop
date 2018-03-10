package com.RunnerClass;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestClassFinder;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.Utilities;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		strict = true,
		monochrome = true, 
		features = "Features",
		glue = "com.TestCases",
		plugin = {"pretty", "html:target/cucumber-html-report" },
		
		tags= {"@Regression"}
		
		)

public class CucumberRunner extends AbstractTestNGCucumberTests {
	
	public static WebDriver driver;
	
	public static ExtentReports extent;
	
	public static ExtentTest test;
	
	public static Fillo fillo;
	
	public static Connection connection;
	
	public static Recordset recordset;
	
	
	public static String resultpath;
	
	
	
	
	@BeforeSuite

	public static void loadTestdata()
	{
		
		//Create a result folder on current date
		resultpath=	Utilities.createResultFolder();
		 connectToExcel();
		 
		 
		 
		// extent=new ExtentReports("Tc_01_ValidateLoginForm.html");	
			
		
	}
	


	
	public static void startTest_Report()
	{	
		test=extent.startTest("Test Execution Status");
	}
	
	
@AfterTest
	
	public static void endTest()
	{
		extent.endTest(test);
		driver.quit();
		extent.flush();
	}
	
//@AfterSuite	
//	
//public static void flushreport()
//{
//	extent.flush();
//	
//	driver=new FirefoxDriver();
//	
//	driver.get("C:\\Users\\tm\\eclipse-workspace\\CucumberTestNg\\Tc_01_ValidateLoginForm.html");
//	
//	driver.manage().window().maximize();
//	
//}


public static String takescreenshot()
{
	String path="";
	
try {
	Thread.sleep(2000);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	TakesScreenshot sht=(TakesScreenshot)driver;
	
	String print=sht.getScreenshotAs(OutputType.BASE64);
	
	
	return "data:image/jpg;base64, " + print ;
	
	
	
}

//###########################################################################################
/*
 * Method Name:-waitForElement
 * Purpose:-This method is developed to maintain a constant wait across all the pages and elements
 * Input Parameters:-User must send the webelement as an argument
 * Output Parameters:-NA
 * Designed By:-
 * Creation Date:-
 * Reviewed By:-
 * Comments:-
 * Modified Date:- 
 */
//###########################################################################################

public static void waitForElement(WebElement elemnt)
{
	for(int i=1;i<=15;i++)
	{
		//Try to perform mouse hovering action on a webelemnt
		try
		{
			System.out.println("Wait is execution");
			Actions acc=new Actions(driver);
			acc.moveToElement(elemnt).build().perform();
			System.out.println("Wait is completed and elemnt found");
			
			//if an element is found then break the execution loop
			break;
		}
		catch(Exception e)
		{
			//if an element is not found then wait for 1 sec
			try
			{		
				Thread.sleep(1000);
				
			}
			catch(Exception e1)
			{
				
			}
			
			
			
			
		}
		
		
	}
	
	
}

//##########################################################################
/* Method name:connectToExcel
 * 
 * Purpose:For fast retrival of data we re converting excel as database
 * 
 * Plugin Depency:Dependency is added in POM.XML
 * 
 * Input parameter:NA
 * 
 * Output Parameter:NA
 */

//##########################################################################

public static void connectToExcel()
{
	
	fillo=new Fillo();
	
    try {
    	
    	String crntdir=System.getProperty("user.dir");
    	
    	
		connection=fillo.getConnection(crntdir+"\\Testdata\\Testdata.xlsx");	
		
		System.out.println("********** Connection Estalblished sucessfully *********");
		
	} catch (FilloException e) {
		
		e.printStackTrace();
	}	
}

//##########################################################################
/* Method name:getData
* 
* Purpose:Get the data for a particular test case with respective to the iteration
* 
* Plugin Depency:Dependency is added in POM.XML
* 
* Input parameter:Testcasename,Iteration
* 
* Output Parameter:String data
*/

//##########################################################################

public static String getData(String sheetname,String Testcasename,String fieldname,int itr)
{
	
	String data="";	
	try
	{		
		String strQuery="Select "+fieldname+" from "+sheetname+" where Tc_Name='"+Testcasename+"' and Iteration="+itr;
		
		recordset=connection.executeQuery(strQuery);
		 
		while(recordset.next()){
		System.out.println(recordset.getField(fieldname));
		data=recordset.getField(fieldname);
		
		break;
		}
		
	}
	
	catch(Exception e)
	{
		System.out.println("No Records Found");
		
	}
	finally
	{
		recordset.close();
	}
	

		return data;
	
	
}


public static int getIterationCount(String sheetname,String Testcasename)
{
	
	int  data=1;	
	try
	{		
		String strQuery="Select "+Testcasename+" from "+sheetname;
		
		recordset=connection.executeQuery(strQuery);
		 
		while(recordset.next()){
		
		data++;
		
		}
		
	}
	
	catch(Exception e)
	{
		System.out.println("No Records Found");
		
	}
	finally
	{
		recordset.close();
	}
	

		return data;
	
	
}



public static void intializeReport(String classname) {
	extent=new ExtentReports(resultpath+"\\"+classname+".html");	
	
}



public static void logEvent(String status,String Description)
{
	switch(status.toLowerCase())
	{
	
	case "pass" :
		test.log(LogStatus.PASS, Description+test.addBase64ScreenShot(takescreenshot()));
	break;
	
	case "fail" :
		test.log(LogStatus.FAIL, Description+test.addBase64ScreenShot(takescreenshot()));
	break;
	
	case "error" :
		test.log(LogStatus.ERROR, Description);
	break;
	
	case "warning" :
		test.log(LogStatus.WARNING, Description+test.addBase64ScreenShot(takescreenshot()));
	break;
	
	}
	
}



}


