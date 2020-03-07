package test.java.com.imdb.tests;

import java.lang.reflect.Method;

import main.java.com.imdb.pages.ImdbAccountCreationLandingPage;
import main.java.com.imdb.pages.ImdbCreateAccountPage;
import main.java.com.imdb.pages.ImdbHomePage;
import main.java.com.imdb.pages.ImdbSignInPage;
import main.java.com.seleniumgridmultiplethread.project.BaseTest;
import org.testng.annotations.Test;
import com.mailbox.pages.MailBox;


public class ImdbTests extends BaseTest {

	/**Note: We will be using Email Service--> "https://www.mohmal.com/en" for generating Temp Emails and creating Account 
	I have used reflections to fetch the Test Case Name while running the Test Cases
	Approach: I have used TestNG, Maven , Selenium and Java for this assignment
	1.) BaseTest: This is the Base Test Class which contains @BeforeMethod(for setup Chrome driver) and @AfterMethod(for tear down the Chrome Driver)
	2.) Base Test: Also contains Utility functions that we will use while writing Actions in Page Classes.
	3.) Page Object Model Approach has been used here, we have divided the prokect in Pages for two Websites i.e for IMDB(AUT, Application under Test) and Temp Mail Website(https://www.mohmal.com/en)
	4.) Page Factory has been used with annotations @FindBy to find WebElement. We use initElements method to initialize web elements
	
	Limitations:
	1.) I have not used any logging for example we can use log4j to implement logs and see the execution of test cases in a better way
	2.) I have used only Chrome Driver as of now, we can extend the scope of browsers by introducing @parameters in TestNg, to parametrize more browsers, we can use cloud service like Sauce labs, Selenium Grid ,Docker , Zalenium to increase the scope of browsers
	3.) I have not used any reporting tool example: Extent Reports or Allure Reports, to widen the scope we can introduce some reporting mechanism as well
	4.) There is no Jenkins job to run the test cases, we can Create Jenkins Job , keeping our code in Git or some other SCM tools
	5.) There is no support of API in existing project, we can increase that scope by introducing Rest Assured or Apache HTTP Client
	6.) There is no support for catching Browsers Java Script Errors, we can introduce some Library example--> "JSErrorCollector" 
	 **/
	
	/*@Test
	public void createAccountPositiveTestCase(Method m){

		//Using reflections to get Test Case Name
		System.out.println("Running Test case-->" + m.getName());
		//Creating Objects of Page classes in Test Cases , so that scope of Objects is valid only for this test case and memory utilization is less
		ImdbHomePage imdbHomePage=new ImdbHomePage(driver);
		ImdbSignInPage imdbSignInPage=new ImdbSignInPage(driver);
		ImdbCreateAccountPage imdbCreateAccountPage=new ImdbCreateAccountPage(driver);
		MailBox mailBox=new MailBox(driver);
		ImdbAccountCreationLandingPage imdbAccountCreationLandingPage=new ImdbAccountCreationLandingPage(driver);

		//Starting the Test Scenario
		imdbHomePage.signIn();
		waitForPageToLoad("https://www.imdb.com/registration/signin?");
		imdbSignInPage.createAccount();
		waitForPageToLoad("https://www.imdb.com/ap/register");
		//HashMap to Store Email Address, WindowHandle name of Imdb and MailBox Window, As we will be switching the windows from Mailbox to Imdb for getting Email address from Mail Box and creating an Account in IMDB
		HashMap<String, String> hm=mailBox.returnEmailAddressandWindowHandleName();
		String username="Test";
		//Creating Account in IMDB with Email Address retrieved from MailBox
		imdbCreateAccountPage.createAccount(username,hm.get("emailAddr"));
		String loginName=imdbAccountCreationLandingPage.returnLoginName();
		//Validating that same Username with which we have created account is displayed on the top right side of the IMDB Creation Landing Page 
		Assert.assertEquals(username, loginName,"Looks Like some issue in creating Account, Pls Check Username");
		switchToWindow(hm.get("MailBox"));
		mailBox.goToVerificationEmail();

		switchToWindow();
		String confirmatioPageUrl=driver.getCurrentUrl();
		String expectedUrlConfirmationPage="https://www.imdb.com/registration/confirmation";
		//Assertion to validate user is registered and email is verified
		Assert.assertTrue(confirmatioPageUrl.contains(expectedUrlConfirmationPage),"Looks Like confirmation page is not loaded");

	}

	@Test
	public void createAccountPasswordMisMatch(Method m){

		//Using reflections to get Test Case Name
		System.out.println("Running Test case-->" + m.getName());

		ImdbHomePage imdbHomePage=new ImdbHomePage(driver);
		ImdbSignInPage imdbSignInPage=new ImdbSignInPage(driver);
		ImdbCreateAccountPage imdbCreateAccountPage=new ImdbCreateAccountPage(driver);

		//Starting the Test Scenario
		imdbHomePage.signIn.click();
		waitForPageToLoad("https://www.imdb.com/registration/signin?");
		imdbSignInPage.createAccount.click();
		waitForPageToLoad("https://www.imdb.com/ap/register");
		String username="Test";
		//Validating Error Message appears while Creating Account with Password and ReEnter Password mismatch 
		imdbCreateAccountPage.createAccountParametrize(username, "TestingImdb23@gmail.com", "Password", "PasswordMismatch");
		String errorMessage=imdbCreateAccountPage.getErrorMessage();
		//Assertion to Validate Error message is shown "Passwords must match"
		Assert.assertEquals(errorMessage.trim(), "Passwords must match","Looks Like Message is not proper in case of Password Mismatch");
	}

	@Test
	public void createAccountInvalidEmailAddress(Method m){
		//Using reflections to get Test Case Name
		System.out.println("Running Test case-->" + m.getName());

		ImdbHomePage imdbHomePage=new ImdbHomePage(driver);
		ImdbSignInPage imdbSignInPage=new ImdbSignInPage(driver);
		ImdbCreateAccountPage imdbCreateAccountPage=new ImdbCreateAccountPage(driver);

		//Starting the Test Scenario
		imdbHomePage.signIn.click();
		waitForPageToLoad("https://www.imdb.com/registration/signin?");
		imdbSignInPage.createAccount.click();
		waitForPageToLoad("https://www.imdb.com/ap/register");
		String username="Test";
		//Validating Error Message appears while Creating Account with Invalid Email Address 
		imdbCreateAccountPage.createAccountParametrize(username, "Testing", "Nakul@19a", "Nakul@19a");
		String errorMessage=imdbCreateAccountPage.getErrorMessage();
		//Assertion to Validate Error message is shown "Enter a valid email address"
		Assert.assertEquals(errorMessage.trim(), "Enter a valid email address","Looks Like Message is not proper in case of Invalid Email Address");
	}

	@Test
	public void createAccountWithoutMandatoryFields(Method m){
		//Using reflections to get Test Case Name
		System.out.println("Running Test case-->" + m.getName());

		ImdbHomePage imdbHomePage=new ImdbHomePage(driver);
		ImdbSignInPage imdbSignInPage=new ImdbSignInPage(driver);
		ImdbCreateAccountPage imdbCreateAccountPage=new ImdbCreateAccountPage(driver);

		//Starting the Test Scenario
		imdbHomePage.signIn.click();
		waitForPageToLoad("https://www.imdb.com/registration/signin?");
		imdbSignInPage.createAccount.click();
		waitForPageToLoad("https://www.imdb.com/ap/register");
		//Validating Error Messages are shown when User tries to create account without entering mandatory fields
		imdbCreateAccountPage.createAccountParametrize("", "", "", "");
		//Using List to store Error Messages 
		List<String> errorMessages=imdbCreateAccountPage.getErrorMessages();
		//Assertion to validate Error Messages are shown when User tries to create account without entering mandatory fields
		Assert.assertEquals(errorMessages.get(0).trim(), "Enter your name","Looks Like Message for Name as Mandatory field is missing");
		Assert.assertEquals(errorMessages.get(1).trim(), "Enter your email","Looks Like Message for Email as Mandatory field is missing");
		Assert.assertEquals(errorMessages.get(2).trim(), "Enter your password","Looks Like Message for Password as Mandatory field is missing");


	}

	@Test
	public void validateHyperLinksOnCreateAccountPage(Method m){
		//Using reflections to get Test Case Name
		System.out.println("Running Test case-->" + m.getName());

		ImdbHomePage imdbHomePage=new ImdbHomePage(driver);
		ImdbSignInPage imdbSignInPage=new ImdbSignInPage(driver);
		ImdbCreateAccountPage imdbCreateAccountPage=new ImdbCreateAccountPage(driver);

		//Starting the Test Scenario
		imdbHomePage.signIn.click();
		waitForPageToLoad("https://www.imdb.com/registration/signin?");
		imdbSignInPage.createAccount.click();
		waitForPageToLoad("https://www.imdb.com/ap/register");
		//Creating a list to store Title of all the windows, when user clicks on Hyperlinks present on Create Account Page
		List<String> titlesOfHyperLinks=imdbCreateAccountPage.checkHyperLinks();

		//Assertion to Validate Title of each HyperLink clicked on Create Account Page
		Assert.assertTrue(titlesOfHyperLinks.contains("Ratings and Reviews for New Movies and TV Shows - IMDb"));
		Assert.assertTrue(titlesOfHyperLinks.contains("IMDb Sign In"));
		Assert.assertTrue(titlesOfHyperLinks.contains("IMDb | Help"));
		Assert.assertTrue(titlesOfHyperLinks.contains("Conditions of Use - IMDb"));
		Assert.assertTrue(titlesOfHyperLinks.contains("Privacy - IMDb"));

	}*/


	@Test
	public void createAccount(Method m){

		getDriver().get("https://www.imdb.com");
		System.out.println("IMDB Test Started! " + "Thread Id: " +  Thread.currentThread().getId());
		//Using reflections to get Test Case Name
		System.out.println("Running Test case-->" + m.getName());
		//Creating Objects of Page classes in Test Cases , so that scope of Objects is valid only for this test case and memory utilization is less
		ImdbHomePage imdbHomePage=new ImdbHomePage(driver);
		ImdbSignInPage imdbSignInPage=new ImdbSignInPage(driver);
		ImdbCreateAccountPage imdbCreateAccountPage=new ImdbCreateAccountPage(driver);
		MailBox mailBox=new MailBox(driver);
		ImdbAccountCreationLandingPage imdbAccountCreationLandingPage=new ImdbAccountCreationLandingPage(driver);

		//Starting the Test Scenario
		imdbHomePage.signIn();
		waitForPageToLoad("https://www.imdb.com/registration/signin?");
		imdbSignInPage.createAccount();
		waitForPageToLoad("https://www.imdb.com/ap/register");
	}

}