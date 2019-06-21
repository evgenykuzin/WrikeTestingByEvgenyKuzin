import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.allure.junit.AllureRunListener;

import java.util.concurrent.TimeUnit;

public class WrikeTest {

    private static JUnitCore runner;
    private static WebDriver driver;
    private static WebElement element;
    private static WebDriverSteps webDriverSteps;
    @BeforeAll
    public static void init() {
        runner = new JUnitCore();
        runner.addListener(new AllureRunListener());
        runner.run();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        webDriverSteps = new WebDriverSteps(driver);
       // webDriverSteps.openMainPage();
    }

    @Test
    public void nextPageOpened(){
        webDriverSteps.openMainPage();
        webDriverSteps.submitEmail();
        String expected = "Thank you for choosing Wrike!";
        element = driver.findElement(By.xpath(Xpaths.H1_PATH));
        String actual = element.getText();
        Assertions.assertTrue(actual.contains(expected));
    }

    @Test
    public void answersSubmitted(){
        webDriverSteps.openMainPage();
        webDriverSteps.submitEmail();
        webDriverSteps.answerTheQuestions();
        try {
            Assertions.assertTrue(driver.findElement(By.xpath(Xpaths.BTN_SUBMITTED_PROGRESS_PATH)).isEnabled());
        } catch (NoSuchElementException nsee) {
            Assertions.fail("Fail: results was not submitted, button was not clicked");
        }
    }

    @Test
    public void emailResend(){
        webDriverSteps.openMainPage();
        webDriverSteps.submitEmail();
        webDriverSteps.resendEmail();
        try {
            Assertions.assertTrue(driver.findElement(By.xpath(Xpaths.BTN_RESEND_PROGRESS_PATH)).isEnabled());
        } catch (NoSuchElementException nsee) {
            Assertions.fail("Fail: resending was failed, 'resend email' button was not clicked");
        }
    }

    @Test
    public void twitterTest(){
        //check Twitter link
        webDriverSteps.openMainPage();
        element = driver.findElement(By.xpath(Xpaths.TWITTER_LINK_PATH));
        Assertions.assertEquals("https://twitter.com/wrike", element.getAttribute("href"));
        webDriverSteps.submitEmail();
        Assertions.assertEquals("https://twitter.com/wrike", element.getAttribute("href"));
        //check Twitter icon
        // don't understand 'svg' :( this test i'v failed...
        //there must be Assertion...

        //driver.quit();
    }

    @AfterAll
    public static void quit(){
        webDriverSteps.quit();
    }

}



