package basepkg;

import java.sql.ResultSet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import swagpagepkg.Swagloginpage;

public class Swagbase {

public static WebDriver driver;
	
	public Swagloginpage loginobj;
	
	public static ExtentSparkReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest test; 
	
	@BeforeTest
	public void setup()
	{
		reporter = new ExtentSparkReporter("./Reports/myreport1.html");
		reporter.config().setDocumentTitle("AutomationReport");
		reporter.config().setReportName("Functional test");
		reporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Hostname", "Localhost");
		extent.setSystemInfo("OS", "windows10");
		extent.setSystemInfo("Tester name", "abc");
		extent.setSystemInfo("Browser name", "chrome");
		
		
		//open in incognito to manage browser popup
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");		
		driver = new ChromeDriver(options);
		
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		
	}
	
	@BeforeMethod
	public void objectmethod()
	{
		loginobj = new Swagloginpage(driver);
	}
	
	@AfterMethod
	public void aftmthd(ITestResult result)
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			test.log(Status.FAIL, "test case failed is " + result.getName());
			test.log(Status.FAIL, "test case failed is " + result.getThrowable());
			
		}
		
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			test.pass("test passed: " + result.getName());
		}
		
		else if(result.getStatus() == ITestResult.SKIP)
		{
			test.skip("test skipped: " + result.getName());
		}
	}
	
	@AfterTest
	public void endreport()
	{
		extent.flush();
		driver.quit();
	}
}
