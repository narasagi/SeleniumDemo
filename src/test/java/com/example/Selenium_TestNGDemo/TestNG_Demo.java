package com.example.Selenium_TestNGDemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
 
public class TestNG_Demo {
    WebDriver driver;
 
    By userName = By.name("username");
    By passWord = By.name("password");
 
    By loginBtn = By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button");
 
    By loginTitle = By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/h5");
 
    By dashboardPage = By.xpath("//*[@id='app']/div[1]/div[1]/header/div[1]/div[1]/span/h6");
 
 
    @BeforeMethod
    public void setUp() {
 
        System.out.println("Open a Chrome Web Browser");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--start-maximized");
        driver=new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/");
        System.out.println("Open the application");
    }
 
    @Test(description = "This test validates title of login functionality", priority = 0)
    public void verifyLoginPageTitle() {
 
        System.out.println("Verify the Login page title");
        String expectedTitle = driver.findElement(loginTitle).getText();
 
        System.out.println("Actual Title :" + expectedTitle);
        Assert.assertTrue(expectedTitle.equalsIgnoreCase("Login"));
    }
 
    @Test(description = "This test validates  successful login to Home page", priority = 1)
    public void verifyloginPage() {
 
        System.out.println("Enter Username");
        driver.findElement(userName).sendKeys("Admin");
 
        System.out.println("Enter Password");
        driver.findElement(passWord).sendKeys("admin123");
 
        driver.findElement(loginBtn).submit();
 
        System.out.println("New page - Dashboard is opened");
        String newPageText = driver.findElement(dashboardPage).getText();
 
        System.out.println("Heading of new page :" + newPageText);
        Assert.assertTrue(newPageText.contains("Dashboard"));
 
    }
 
    @AfterMethod
    public void teardown() {
 
        System.out.println("Close the webpage");
        driver.quit();
    }
 
}
