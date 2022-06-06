package uiTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import uiTests.baseAbstractClass.BaseAbstractClass;
import uiTests.pageObject.HomeAviasalesPage;

public class TestWithInvalidData extends BaseAbstractClass {

  HomeAviasalesPage homeAviasalesPage = new HomeAviasalesPage();


    @Test(groups = {"regression"})
    public void checkTextforEmptyFieldPlace () throws InterruptedException {

        homeAviasalesPage.openPageHome()
                .fillDeparturePlace("Минск")
                .chooseDateDeparture()
                .setAdultQuantity(2)
                .getSearchResult();
        Assert.assertEquals(homeAviasalesPage.getTextForEmptyFieldArrivalPlace(), "Укажите город прибытия");

    }
    @Test(groups = {"regression"})
    public void checkTextforEmptyFieldDate () throws InterruptedException {

        homeAviasalesPage.openPageHome()
                .fillDeparturePlace("Киев")
                .fillArrivalPlace("Женева")
                .setAdultQuantity(3)
                .getSearchResult();
        Assert.assertEquals(homeAviasalesPage.getTextForEmptyFieldDate(), "Укажите дату");

    }
}
