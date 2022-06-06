package uiTests.pageObject;

import uiTests.webDriverSingleton.WebDriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class AbstractPage {

    protected WebDriver driver;

    protected final int WAIT_TIMEOUT_SECONDS = 20;

    protected static final String URL_HOMEPAGE = "http://aviasales.by/";

    protected static final Logger logger = LogManager.getLogger();

    protected AbstractPage() {

        driver = WebDriverSingleton.getDriver();

        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        }

    protected void waitForElementToBeClickable(WebElement webElement) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS)).until(ExpectedConditions
                .elementToBeClickable(webElement));
    }



    protected WebElement waitForVisibilityOfElement(WebElement webElement) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS)).until(ExpectedConditions
                .visibilityOf(webElement));
    }

    protected void waitForVisibilityOfAllElementsLocatedBy(List<WebElement> webElementList) {
         new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(webElementList));
    }

    protected boolean waitForInvisibilityOfElement(WebElement webElement) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS)).until(ExpectedConditions
                .invisibilityOf(webElement));
    }
}
