package main.java.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
 
public class SampleSauceTest {
 
  public static final String USERNAME = "neeraj19a";
  public static final String ACCESS_KEY = "81f32882-2388-4938-9afc-51d4e2270735";
  public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
 
  public static void main(String[] args) throws Exception {
 
    DesiredCapabilities caps = DesiredCapabilities.safari();
    caps.setCapability("platform", "macOS 10.14");
    caps.setCapability("version", "12.0");
    caps.setCapability("screenResolution", "1024x768");
    caps.setCapability("name", "Google Testing");
    caps.setCapability("capturePerformance", true);
    caps.setCapability("extendedDebugging", true);
    caps.setCapability("buildNumber", "3.0");
    
  
    
 
    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
    
    driver.get("https://google.com");
    System.out.println(driver.getTitle());
    driver.findElement(By.name("q")).click();
    driver.findElement(By.name("q")).sendKeys("hello");
    
    driver.quit();
    System.out.println("Test completed");
    
    
  }
}