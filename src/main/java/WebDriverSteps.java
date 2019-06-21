import org.junit.runner.JUnitCore;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.junit.AllureRunListener;

import java.util.concurrent.TimeUnit;

public class WebDriverSteps {

    private WebDriver driver;
    private static JUnitCore runner;
    private static String inputEmail;
    private static String mainURL;
    private static WebElement element;

    public WebDriverSteps(WebDriver driver) {
        this.driver = driver;
        mainURL = "https://www.wrike.com";
    }

//    public WebDriverSteps(WebDriver driver) {
////        runner = new JUnitCore();
////        runner.addListener(new AllureRunListener());
////        runner.run();
//
//        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//       this.driver = driver;
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    }

    @Step
    public void openMainPage() {
        driver.get(mainURL);
    }

    @Step
    public void submitEmail() {
        inputEmail = RandomGenerator.getRandomEmail();

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
    }

    @Step
    public void answerTheQuestions() {
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
    }

    @Step
    public void resendEmail() {
        //recending email
        element = driver.findElement(By.xpath(Xpaths.RESEND_EMAILE_BUTTON_PATH));
        element.click();
    }

    @Attachment
    @Step("Make screen shot of results page")
    public byte[] makeScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step
    public void quit() {
        driver.quit();
    }

}