package swagpagepkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Swagloginpage {

WebDriver driver;
	
	@FindBy(xpath="//*[@id=\"user-name\"]")WebElement username;
	@FindBy(xpath="//*[@id=\"password\"]")WebElement password;
	@FindBy(xpath="//*[@id=\"login-button\"]")WebElement loginbtn;
	
	public Swagloginpage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	public String titlemthd()
	{
		String title = driver.getTitle();
		return title;
	}
	
	public void loginmthd(String un, String pwd)
	{
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginbtn.click();
	}
	
	public String urlmthd()
	{
		String actualurl = driver.getCurrentUrl();
		return actualurl;
	}
}
