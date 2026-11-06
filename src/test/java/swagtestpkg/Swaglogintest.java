package swagtestpkg;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import basepkg.Swagbase;

public class Swaglogintest extends Swagbase {
	@Test (priority=1)
	public void titleverificationmthd()
	{
		test = extent.createTest("titleverification");
		String actualtitle = loginobj.titlemthd();
		Assert.assertEquals(actualtitle,"Swag Lab");	
	}
	
	@Test(priority=2)
	public void loginvalidationmthd()
	{
		test = extent.createTest("loginvalidation");
		loginobj.loginmthd("standard_user", "secret_sauce");
		
	}
	
	@Test(priority = 3)
	public void urlvalidation()
	{
		test = extent.createTest("urlvalidation");
		String homeurl = loginobj.urlmthd();
		System.out.println(homeurl);
		//Assert.assertEquals(homeurl, "https://www.saucedemo.com/inventory.html");
		
		//giving explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
	}
}
