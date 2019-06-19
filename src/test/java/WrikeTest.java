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
    private static String inputEmail;
    private static String mainURL;
    private static WebDriver driver;
    private static WebElement element;

    @BeforeAll
    public static void init() {
        runner = new JUnitCore();
        runner.addListener(new AllureRunListener());
        // runner.addListener(new AllureRunListener());
        runner.run();
        //seting chromedriver.exe path
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mainURL = "https://www.wrike.com";
        driver.get(mainURL);
        inputEmail = RandomGenerator.getRandomEmail();
    }


    @Test
    public void bazeTestScenario() {
        inputEmail = RandomGenerator.getRandomEmail();
        System.out.println("inputEmail = " + inputEmail);

        //click "get started for free"
        element = driver.findElement(By.xpath(Xpaths.TOP_SUBMIT_EMAIL_BUTTON_PATH));
        element.click();

        //input email
        element = driver.findElement(By.xpath(Xpaths.FROM_FORM_INPUT_EMAIL_PATH));
        element.sendKeys(inputEmail);
        System.out.println(element.getText());

        //submit email
        element = driver.findElement(By.xpath(Xpaths.FROM_FORM_SUBMIT_EMAIL_BUTTON_PATH));
        element.click();

        //check new page
        String expected = "Thank you for choosing Wrike!";
        element = driver.findElement(By.xpath(Xpaths.H1_PATH));
        String actual = element.getText();
        Assertions.assertTrue(actual.contains(expected));

        //filling random answers
        element = driver.findElement(By.xpath(RandomGenerator.getRandomAnswer(1)));
        element.click();
        element = driver.findElement(By.xpath(RandomGenerator.getRandomAnswer(2)));
        element.click();
        element = driver.findElement(By.xpath(RandomGenerator.getRandomAnswer(3)));
        element.click();

        //submitting results
        element = driver.findElement(By.xpath(Xpaths.SUBMIT_RESULTS_BUTTON_PATH));
        element.click();

        //check submitting
        try {
            Assertions.assertTrue(element.findElement(By.xpath(Xpaths.BTN_SUBMITTED_PROGRESS_PATH)).isEnabled());
        } catch (NoSuchElementException nsee) {
            Assertions.fail("Fail: results was not submitted, button was not clicked");
        }

        //recending email
        element = driver.findElement(By.xpath(Xpaths.RESEND_EMAILE_BUTTON_PATH));
        element.click();

        //check resending
        try {
            Assertions.assertTrue(element.findElement(By.xpath(Xpaths.BTN_RESEND_PROGRESS_PATH)).isEnabled());
        } catch (NoSuchElementException nsee) {
            Assertions.fail("Fail: resending was failed, 'resend email' button was not clicked");
        }

        //Close the browser
        driver.quit();
    }

    @Test
    public void twitterTest(){
        //check Twitter link
        element = driver.findElement(By.xpath(Xpaths.TWITTER_LINK_PATH));
        Assertions.assertEquals("https://twitter.com/wrike", element.getAttribute("href"));

        //check Twitter icon
        // don't understand 'svg' :( this test i'v failed...
        //there must be Assertion...

        driver.quit();
    }

}



