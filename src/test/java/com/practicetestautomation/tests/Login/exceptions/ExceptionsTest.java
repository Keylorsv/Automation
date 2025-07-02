package com.practicetestautomation.tests.Login.exceptions;

import com.practicetestautomation.tests.Login.login.LoginTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionsTest {
    private WebDriver driver;
    private Logger logger;


    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser){
        logger = Logger.getLogger(LoginTests.class.getName());
        logger.setLevel(Level.INFO);
        System.out.println("Running test in "+ browser);
        System.out.println("==> Ejecutando BeforeMethod");
        switch (browser.toLowerCase()){
            case "chrome":
                driver= new ChromeDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Configuration for "+ browser+ " is missing, so running test in Chrome by Default.");
                driver= new ChromeDriver();
                break;
        }
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
    }
    @Test
    public void noSuchElementExceptionTest(){
        WebElement addButton = driver.findElement(By.id("add_btn"));
        WebElement row2InputField = driver.findElement(By.xpath("//*[@id=\'row2\']/input"));

        addButton.click();
        try {
            Thread.sleep(7000);
        } catch ( Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(row2InputField.isDisplayed(), "Row 2 input flied is not displayed");
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser close");
        }
    }
}
