package com.TestCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.RunnerClass.CucumberRunner;
import com.ScreenFunctions.Login;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tc_01_ValidateLoginForm extends CucumberRunner {
	

	
	
	@Given("^I want to launch the firefox browser$")
	public void runBrowser() {
		intializeReport("Tc_01_ValidateLoginForm");
		startTest_Report();
		driver=new FirefoxDriver();
	}
	
	@And("^i need to maximize it$")
	
	public void maximizeBrowser() {
		   
		driver.manage().window().maximize();
	}
	
	@When("^it maximizes enter the url$")
	
	public void enterURL()
	{
		driver.get("http://testingmasters.com/hrm/symfony/web/index.php/auth/login");
	}
	
	@Then("^I need to enter \"([^\"]*)\" credentials$")
	
	
	public void EnterCredentials(String input)
	{
		System.out.println(input);
		Login lgn=PageFactory.initElements(driver, Login.class);
		
		

		
		
		int itrcount=getIterationCount("Testcases","Tc_01_ValidateLoginForm");
		
		for(int i=1;i<=itrcount-1;i++)
		{
			
			String username=getData("Testcases","Tc_01_ValidateLoginForm","Username",i);
			String password=getData("Testcases","Tc_01_ValidateLoginForm","Password",i);
			
			System.out.println(username);
			System.out.println(password);
			lgn.Login_Admin(username,password);
			
			
		}
		

	
}
	
}
