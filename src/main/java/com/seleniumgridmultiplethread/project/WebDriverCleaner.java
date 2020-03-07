package main.java.com.seleniumgridmultiplethread.project;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.Set;

public class WebDriverCleaner extends BaseTest {

    //TO DO getExecutionMode
    public void cleanWebDriver() {
        closeRedundantWindows(this.driver);
/*
        boolean isDesktop = "desktopWebUI".equalsIgnoreCase(DataStore.get("ExecutionMode"));
        String browser = DataStore.get("browser");
*/

        boolean isDesktop = "desktopWebUI".equalsIgnoreCase("DESKTOPWEBUI");
        String browser = "chrome";

        if (isDesktop) {
            WebDriver.Options manage = getDriver().manage();
            manage.deleteAllCookies();
            if (browser.equalsIgnoreCase("chrome")) {
                manage.window().fullscreen();
            } else {
                manage.window().maximize();
            }
        }
    }

    public void closeRedundantWindows(ThreadLocal<RemoteWebDriver> driver) {
        Set<String> windowHandles = getDriver().getWindowHandles();
        if (windowHandles.size() > 1) {
            getDriver().switchTo().defaultContent();
            String topWindowHandle = getDriver().getWindowHandle();
            for (String windowHandle : windowHandles) {
                if (!windowHandle.equals(topWindowHandle)) {
                    getDriver().switchTo().window(windowHandle);
                    getDriver().close();
                }
            }
        }
    }

    public void openNewTabAndCloseOtherCurrent() {
        getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        ArrayList<String> tabsArr = new ArrayList<String>(getDriver().getWindowHandles());
        if (tabsArr.size() > 1) {
            getDriver().switchTo().window(tabsArr.get(1));
            String topWindowHandle = getDriver().getWindowHandle();
            for (String windowHandle : tabsArr) {
                if (!windowHandle.equals(topWindowHandle)) {
                    getDriver().switchTo().window(windowHandle);
                    getDriver().close();
                }
            }
        }
    }


}
