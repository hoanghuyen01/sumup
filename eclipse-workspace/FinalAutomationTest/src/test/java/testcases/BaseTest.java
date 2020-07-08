package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class BaseTest {

    public WebDriver driver;

    @BeforeTest
    public void initDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
    public void captureScreenshot(String screenshotName)
    {
     
    try 
    {
    TakesScreenshot ts=(TakesScreenshot)driver;
     
    File source=ts.getScreenshotAs(OutputType.FILE);
    
    File DestFile = new File("D://automationtest/"+screenshotName+".png") ;
     
    FileUtils.copyFile(source, DestFile);
     
    System.out.println("Screenshot taken");
    } 
    catch (Exception e)
    {
     
    System.out.println("Exception while taking screenshot "+e.getMessage());
    } 
    }
}
