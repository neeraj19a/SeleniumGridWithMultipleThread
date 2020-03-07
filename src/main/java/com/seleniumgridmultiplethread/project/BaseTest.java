package main.java.com.seleniumgridmultiplethread.project;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    public CapabilityFactory capabilityFactory = new CapabilityFactory();
    private Actions actions = null;
    private JavascriptExecutor js;

    private static int timeout = 10;

    @BeforeMethod
    public void setup() {

        System.setProperty("webdriver.chrome.driver", ".//src//test//java//com//imdb//tests//resources//chromedriver.exe");
        try {
            driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilityFactory.getCapabilities("chrome")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        getDriver().manage().window().maximize();
    }

    public WebDriver getDriver() {
        //Get driver from ThreadLocalMap
        return driver.get();
    }
    @AfterMethod
    public void teardown() {
        getDriver().quit();
    }

    /**
     * This function checks for Page To Load
     *
     * @param pageURL
     * @return
     */
    public boolean waitForPageToLoad(final String pageURL) {

        WebDriverWait wait = new WebDriverWait(getDriver(), 45);
        return wait.until(new ExpectedCondition<Boolean>() {
                              public Boolean apply(WebDriver d) {

                                  boolean flag = (((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
                                  boolean flag1 = (getDriver()).getCurrentUrl().contains(pageURL);
                                  return flag && flag1;

                              }
                          }
        );
    }


    /**
     * Utility function to switch to recent Open Window
     */
    public void switchToWindow() {
        for (String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }
    }

    /**
     * Utility Function to switch between Windows
     *
     * @param windowName
     */
    public void switchToWindow(String windowName) {
        getDriver().switchTo().window(windowName);

    }

    /**
     * Explicit Wait Utility function to wait for particular element
     *
     * @param element
     */
    public void wait(WebElement element) {
        WebDriverWait wait = new WebDriverWait((WebDriver) driver, 45);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }



}
