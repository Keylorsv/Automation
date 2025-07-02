package com.practicetestautomation.tests.Login.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginTests {

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
      driver.get("https://practicetestautomation.com/practice-test-login/");
   }


   public void Login(String username, String password){
      if (driver == null) {
         throw new RuntimeException();
      }
      WebElement usernameInput = driver.findElement(By.id("username"));
      WebElement passwordInput = driver.findElement(By.id("password"));
      WebElement submitButton = driver.findElement(By.id("submit"));

      logger.info("Typing username: "+ username);
      usernameInput.sendKeys(username);
      logger.info("Typing password: "+ password);
      passwordInput.sendKeys(password);

      assertElementEnabled(submitButton,"Submit button");
      submitButton.click();
      try {
         Thread.sleep(500);
      } catch ( Exception e) {
         throw new RuntimeException(e);
      }
   }
   private void assertAlertErrorMessage(String expectedMessage) {
      WebElement errorAlert = driver.findElement(By.id("error"));
      assertElementDisplayed(errorAlert,"Error alert");
      logger.info("Verifying the error message, the expected error message was "+expectedMessage);
      Assert.assertEquals(errorAlert.getText(), expectedMessage, "Unexpected error message, the expected error message was "+expectedMessage);
   }
   private void assertElementDisplayed(WebElement element, String expectedElement){
      Assert.assertTrue(element.isDisplayed(), "The expected: "+expectedElement+" wasn't displayed");
   }
   private void assertElementEnabled(WebElement element, String expectedElement){
      Assert.assertTrue(element.isEnabled(), "The expected: "+expectedElement+" aren't enable");
   }

   @Parameters({"username","password","expectedMessage"})
   @Test(groups = {"positive", "regression", "smoke"})
   public void TestLoginFunctionality(String username,String password, String expectedMessage){
      logger.info("Starting Login test functionality");
//        Type username student into Username field
//        Type password Password123 into Password field
      Login(username, password);

//        Verify new page URL contains practicetestautomation.com/logged-in-successfully/
      String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
      String currentUrl = driver.getCurrentUrl();
      Assert.assertEquals(currentUrl,expectedUrl, "Login fail");

//        Verify new page contains expected text ('Congratulations' or 'successfully logged in')

      String pageSource = driver.getPageSource();

      Assert.assertTrue(pageSource.contains(expectedMessage),"Not show the message");

//        Verify button Log out is displayed on the new page
      WebElement logOutButton = driver.findElement(By.xpath("//a[@class='wp-block-button__link has-text-color has-background has-very-dark-gray-background-color']"));
      Assert.assertTrue(logOutButton.isDisplayed(),"Log out button its not displayed");

   }
   @Parameters({"username", "password", "expectedErrorMessage"})
   @Test(groups = {"negative","regression"})
   public void NegativeLoginTest(String username, String password, String expectedErrorMessage){
      logger.info("starting Negative login test");

      Login(username,password);
      assertAlertErrorMessage(expectedErrorMessage);
   }

   @AfterMethod(alwaysRun = true)
   public void tearDown() {
      if (driver != null) {
         driver.quit();
         logger.info("Browser close");
      }
   }

}
