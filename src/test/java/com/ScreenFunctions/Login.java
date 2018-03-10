package com.ScreenFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.RunnerClass.CucumberRunner;
import com.relevantcodes.extentreports.LogStatus;

public class Login extends CucumberRunner {
	
	
	@FindBy(how=How.ID,using="txtUsername")
	public static WebElement Edi_UserName;
	
	@FindBy(how=How.ID,using="txtPassword")
	public static WebElement Edi_Password;

	@FindBy(how=How.ID,using="btnLogin")
	public static WebElement Btn_Login;
	
	
	@FindBy(how=How.ID,using="welcome")
	public static WebElement Lnk_Welcome;
	
	@FindBy(how=How.XPATH,using="//a[text()='Logout']")
	public static WebElement Lnk_Logout;
	
	
	
	
	public void Login_Admin(String username,String Password)
	{
		try
		{
		Edi_UserName.sendKeys(username);
		Edi_Password.sendKeys(Password);
		Btn_Login.click();
		
		
		//Thread.sleep(2000);
		waitForElement(Lnk_Welcome);		
		Lnk_Welcome.click();
		
		waitForElement(Lnk_Logout);
		Lnk_Logout.click();
		
		waitForElement(Edi_UserName);
		
		
		//test.log(LogStatus.PASS, "Login is sucessfully"+test.addBase64ScreenShot(takescreenshot()));
		logEvent("pass","Login is sucessfully");
		}
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Login is not sucessfully done");
		}
	}

}
