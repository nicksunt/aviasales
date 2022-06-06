package uiTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import uiTests.baseAbstractClass.BaseAbstractClass;
import uiTests.pageObject.AuthorizationPage;

public class TestAuthorization extends BaseAbstractClass {

    AuthorizationPage authorizationPage = new AuthorizationPage();


    @Test(groups = "smoke")
    public void checkAuthorization() throws InterruptedException {

        authorizationPage.openPageHome()
                .openWindowAuthorization()
                .fillPhoneNumber()
                .fillPassword()
                .authorization()
                .switchToAnotherWindow();

        Assert.assertEquals(authorizationPage.getTextButtonEnterExit(), "Выйти");
    }
}
