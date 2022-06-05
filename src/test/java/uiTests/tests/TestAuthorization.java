package uiTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import uiTests.baseAbstractClass.BaseAbstractClass;
import uiTests.pageObject.AuthorizationPage;

public class TestAuthorization extends BaseAbstractClass {

    AuthorizationPage authorizationPage = new AuthorizationPage();


    @Test(groups = "SMOKE")
    public void checkAuthorization() throws InterruptedException {

        authorizationPage.openPageHome()
                         .authorization();
        Assert.assertEquals(authorizationPage.getTextButtonEnterExit(), "Выйти");

    }
}
