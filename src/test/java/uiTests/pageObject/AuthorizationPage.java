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

    @FindBy(xpath=("//div[@data-test-id='dropdown']//button"))  // неправильно исправил, переделать обратно надо,т.к. надпись войти меняется на выйти
     WebElement buttonEnterExit;

    @FindBy(xpath=("//div[@class ='login_form_container']//div/div"))
    WebElement textForEmptyFieldAuthorization;

    String mainWinID;
    String newWinID;

    public AuthorizationPage openPageHome() {
        driver.get(URL_HOMEPAGE);
        return this;
    }

    public AuthorizationPage openWindowAuthorization() throws InterruptedException {
        Thread.sleep(2000);
        profile.click();
        Thread.sleep(2000);
        buttonEnterExit.click();
        Thread.sleep(2000);
        enterWithFB.click();

        Set<String> windowId = driver.getWindowHandles();
        Iterator<String> iterator = windowId.iterator();
        mainWinID = iterator.next();
        newWinID = iterator.next();
        driver.switchTo().window(newWinID);
        return this;
    }

    public AuthorizationPage fillPhoneNumber()  {
        waitForVisibilityOfElement(fieldPhoneNumber).sendKeys("+375293276354");
            return this;
        }
    public AuthorizationPage fillPassword() { waitForVisibilityOfElement(fieldPassword).sendKeys("ItAcAdEmIyA");
        return this;
    }

    public AuthorizationPage authorization()  {

        waitForElementToBeClickable(buttonEnter).click();
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
    //  waitForVisibilityOfElement(textForEmptyFieldAuthorization).isDisplayed();
        Thread.sleep(4000);
      return textForEmptyFieldAuthorization.getText();
    }

    public String getTextButtonEnterExit() throws InterruptedException {
      //  Thread.sleep(5000);
       waitForElementToBeClickable(profile).click();
        return buttonEnterExit.getText();
    }
}
