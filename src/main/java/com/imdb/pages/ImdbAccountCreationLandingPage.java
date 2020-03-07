package main.java.com.imdb.pages;

import main.java.com.seleniumgridmultiplethread.project.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ImdbAccountCreationLandingPage extends BaseTest {


	public ImdbAccountCreationLandingPage(ThreadLocal<RemoteWebDriver> driver)
	{
		this.driver=driver;
		PageFactory.initElements(getDriver(), this);
	}


	@FindBy(id="nbusername")
	public WebElement yourName;

	public String returnLoginName(){
		return yourName.getText();
	}

}
