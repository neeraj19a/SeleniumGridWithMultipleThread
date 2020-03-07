package main.java.com.imdb.pages;

import main.java.com.seleniumgridmultiplethread.project.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ImdbSignInPage extends BaseTest {

	public ImdbSignInPage(ThreadLocal<RemoteWebDriver> driver)
	{
		this.driver=driver;
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath="//a[contains(@href,'https://www.imdb.com/ap/register')]")
	public WebElement createAccount;
	
	//Actions
	public void createAccount(){
		createAccount.click();
	}
}
