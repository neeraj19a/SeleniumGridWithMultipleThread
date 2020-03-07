package com.mailbox.pages;

import java.util.HashMap;

import main.java.com.seleniumgridmultiplethread.project.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class MailBox extends BaseTest {

	
	public MailBox(ThreadLocal<RemoteWebDriver> driver)
	{
		this.driver=driver;
		PageFactory.initElements( getDriver(), this);
	}

	@FindBy(id="rand")
	public WebElement randomName;
	
	@FindBy(xpath="//div[@id='email']/div[1]")
	public WebElement emailAddress;
	

	@FindBy(xpath="//a[@id='refresh']")
	public WebElement refreshButton;
	
	
	@FindBy(xpath="//*[@id='inbox-table']/tbody/tr/td[1]/a")
	public WebElement welcomeMail;
	
	@FindBy(xpath="//iframe[contains(@src,'/en/message')]")
	public WebElement iFrameMail;
	
	
	@FindBy(xpath="//*[@id='content-wrapper']/table/tbody/tr/td/div/p[3]/a")
	public WebElement verifyMail;
	
	
	/**
	 * This function is user to Store Email Address and Window Handle name of the windows
	 * @return HashMap<String,String>
	 */
	public HashMap<String, String> returnEmailAddressandWindowHandleName(){
		String winHandleBefore = getDriver().getWindowHandle();
		//getDriver().executeScript("window.open('https://www.mohmal.com/en')");
		String winHandleAfter="";
		for(String winHandle : getDriver().getWindowHandles()){
		    winHandleAfter=winHandle;
			getDriver().switchTo().window(winHandle);
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitForPageToLoad("https://www.mohmal.com/en");
		
		randomName.click();
		String emailAddr=emailAddress.getText();
		HashMap<String, String> hm=new HashMap<>();
		hm.put("Imdb",winHandleBefore);
		hm.put("MailBox",winHandleAfter);
		hm.put("emailAddr", emailAddr);
		getDriver().switchTo().window(hm.get("Imdb"));
		return hm;
	}
	
	/**
	 * This function is used to Verify the Email generated
	 */
	public void goToVerificationEmail(){
		refreshButton.click();
		welcomeMail.click();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getDriver().switchTo().frame(iFrameMail);
		wait(verifyMail);
		verifyMail.click();
		
	}
}
