package uiTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import uiTests.baseAbstractClass.BaseAbstractClass;
import uiTests.pageObject.AuthorizationPage;

public class TestWithInvalidAuthorization extends BaseAbstractClass {

    AuthorizationPage authorizationPage = new AuthorizationPage();

    @Test(groups = {"regression"})
    public void checkAlertForEmptyPhoneNumber() throws InterruptedException {
        authorizationPage.openPageHome()
                .openWindowAuthorization()
                .fillPassword()
                .authorization();
        Assert.assertEquals(authorizationPage.getTextInvalidAuthorization(), "Неверный электронный адрес или номер телефона");
    }
}



