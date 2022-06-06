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

    @FindBy(xpath=("//div[@class ='login_form_container']//div/div"))
    WebElement textForEmptyFieldAuthorization;

    String mainWinID;
    String newWinID;

    public AuthorizationPage openPageHome() {
        driver.get(URL_HOMEPAGE);
        return this;
    }

    public AuthorizationPage openWindowAuthorization() {
        profile.click();
        buttonEnterExit.click();
        enterWithFB.click();

        Set<String> windowId = driver.getWindowHandles();
        Iterator<String> iterator = windowId.iterator();
        mainWinID = iterator.next();
        newWinID = iterator.next();
        driver.switchTo().window(newWinID);
        return this;
    }

    public AuthorizationPage fillPhoneNumber() throws InterruptedException {
        Thread.sleep(1000);
            waitForVisibilityOfElement(fieldPhoneNumber).sendKeys("+375293276354");
            return this;
        }
    public AuthorizationPage fillPassword() throws InterruptedException {
        Thread.sleep(1000);
        fieldPassword.sendKeys("ItAcAdEmIyA");
        return this;
    }

    public AuthorizationPage authorization() throws InterruptedException {
        Thread.sleep(1000);
        buttonEnter.click();
        return this;
    }
    public void switchToAnotherWindow(){
        try{
            if(waitForInvisibilityOfElement(fieldPhoneNumber))
                driver.switchTo().window(mainWinID);
        }
        catch (NoSuchWindowException e) {
            driver.switchTo().window(mainWinID);
        }
    }

    public String getTextInvalidAuthorization() throws InterruptedException {
        Thread.sleep(4000);
      return textForEmptyFieldAuthorization.getText();
    }

    public String getTextButtonEnterExit() throws InterruptedException {
        Thread.sleep(5000);
        profile.click();
        return buttonEnterExit.getText();
    }
}
