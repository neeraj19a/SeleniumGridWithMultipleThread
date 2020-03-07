package main.java.com.imdb.pages;
import java.util.ArrayList;
import java.util.List;

import main.java.com.seleniumgridmultiplethread.project.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ImdbCreateAccountPage extends BaseTest {

	public ImdbCreateAccountPage(ThreadLocal<RemoteWebDriver> driver)
	{
		this.driver=driver;
		PageFactory.initElements( getDriver(), this);
	}

	@FindBy(id="ap_customer_name")
	public WebElement yourName;

	@FindBy(id="ap_email")
	public WebElement email;

	@FindBy(id="ap_password")
	public WebElement password;

	@FindBy(id="ap_password_check")
	public WebElement reenterPassword;

	@FindBy(id="continue")
	public WebElement createAccount;

	@FindBy(xpath="//*[@id='auth-error-message-box']/div/h4/../div/ul/li/span")
	public WebElement errorMessage;

	@FindBy(xpath="//*[@id='auth-error-message-box']/div/h4/../div/dl/li")
	public List<WebElement> errorMessages;

	@FindBy(xpath="//a[contains(@href,'https')]")
	public List<WebElement> createAccountPageHyperLinks ;


	//Actions
	/**
	 * Create Account Function to create A new Account on IMDB Site
	 * @param username
	 * @param emailAddress
	 */
	public void createAccount(String username,String emailAddress){
		yourName.click();
		yourName.sendKeys(username);
		email.sendKeys(emailAddress);
		password.sendKeys("Nakul@19a");
		reenterPassword.sendKeys("Nakul@19a");
		createAccount.click();

	}


	/**
	 * Function to retrieve Error Message occured during Creation Of Account
	 * @return
	 */
	public String getErrorMessage(){
		return errorMessage.getText();
	}
	
	/**
	 * Function to retrieve List of Error Messages occured during Creation Of Account
	 * @return List<String>
	 */
	public List<String> getErrorMessages(){
		List<String> errorMsgList=new ArrayList<String>();
		wait(errorMessages.get(0));
		for(WebElement element:errorMessages){
			errorMsgList.add(element.getText());
		}
		return errorMsgList;
	}


	/**
	 * Parametrized Function to create New Account on Imdb Site
	 * @param username
	 * @param emailAddress
	 * @param passwordText
	 * @param confirmPassword
	 */
	public void createAccountParametrize(String username,String emailAddress,String passwordText, String confirmPassword){
		yourName.click();
		yourName.sendKeys(username);
		email.sendKeys(emailAddress);
		password.sendKeys(passwordText);
		reenterPassword.sendKeys(confirmPassword);
		createAccount.click();
	}

	/**
	 * Function To Switch to HyperLinks on Create Account Page
	 * @return
	 */
	public List<String> checkHyperLinks(){

		List<String> titlesOfHyperlinks=new ArrayList<>();
		wait(createAccountPageHyperLinks.get(0));
		String mainWindowHandle=getDriver().getWindowHandle();

		List<WebElement> elements=new ArrayList<>(createAccountPageHyperLinks);
		for(int i=0;i<elements.size();i++){
			//First Two Hyperlinks Open in same window, So we won't use here Switch Window, we can navigate back in same window
			if(i==0||i==1){
				elements.get(i).click();
				titlesOfHyperlinks.add(getDriver().getTitle());
				getDriver().navigate().back();
				wait(createAccountPageHyperLinks.get(0));
				elements=createAccountPageHyperLinks;
			}
			//Last three Hyperlinks Open in new window, So we would use here Switch Window
			else{
				elements.get(i).click();
				switchToWindow();
				titlesOfHyperlinks.add(getDriver().getTitle());
				getDriver().switchTo().window(mainWindowHandle);
				wait(createAccountPageHyperLinks.get(0));
				elements=createAccountPageHyperLinks;

			}
		}
		return titlesOfHyperlinks;
	}

	/**
	 * This function checks for Page To Load
	 * @param pageURL
	 * @return
	 */
	public  boolean waitForPageToLoad(final String pageURL)
	{

		WebDriverWait wait = new WebDriverWait((WebDriver) driver, 45);
		return wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {

				boolean flag=(((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
				boolean flag1=getDriver().getCurrentUrl().contains(pageURL);
				return flag && flag1;

			}
		}
				);
	}



}
