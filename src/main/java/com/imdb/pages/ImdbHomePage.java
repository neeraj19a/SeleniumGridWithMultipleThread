package main.java.com.imdb.pages;

import main.java.com.seleniumgridmultiplethread.project.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ImdbHomePage extends BaseTest {

	public ImdbHomePage(ThreadLocal<RemoteWebDriver> driver)
	{
		this.driver=driver;
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath="//*[contains(@class,'__signin-text')]")
	public WebElement signIn;

	
	//Actions
	public void signIn(){
		signIn.click();
	}
}
