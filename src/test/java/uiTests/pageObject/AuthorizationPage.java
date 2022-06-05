package uiTests.pageObject;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.Iterator;
import java.util.Set;

public class AuthorizationPage extends AbstractPage{

    @FindBy(xpath=("//div[text()='Профиль']//ancestor::button"))
    WebElement profile;

    @FindBy(xpath=("//label[@id='loginbutton']"))
    WebElement buttonEnter;

    @FindBy(xpath=("//div[text()='Facebook']/ancestor::button"))
    WebElement enterWithFB;

    @FindBy(xpath=("//input[@type='text']"))
    WebElement fieldPhoneNumber;

    @FindBy(xpath=("//input[@type='password']"))
     WebElement fieldPassword;

    @FindBy(xpath=("//button[@type='button' and contains(@data-test-id, 'button')]"))
     WebElement buttonEnterExit;

    String mainWinID;
    String newWinID;

    public AuthorizationPage openPageHome() {
        driver.get(URL_HOMEPAGE);
        return this;
    }

    public void authorization() throws InterruptedException {
        profile.click();
        buttonEnterExit.click();
        enterWithFB.click();

        Set<String> windowId = driver.getWindowHandles();
        Iterator<String> iterator = windowId.iterator();
        mainWinID = iterator.next();
        newWinID = iterator.next();
        driver.switchTo().window(newWinID);

        fieldPhoneNumber.sendKeys("+375293276354");
        fieldPassword.sendKeys("ItAcAdEmIyA");
        buttonEnter.click();

        try{
            if(waitForInvisibilityOfElement(fieldPhoneNumber))
                driver.switchTo().window(mainWinID);
            }
        catch (NoSuchWindowException e) {
            driver.switchTo().window(mainWinID);
        }
        Thread.sleep(2000);
    }

    public String getTextButtonEnterExit() {
        profile.click();
        return buttonEnterExit.getText();
    }
}
