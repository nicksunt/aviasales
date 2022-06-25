package uiTests.tests;

import org.testng.annotations.DataProvider;
import uiTests.baseAbstractClass.BaseAbstractClass;
import uiTests.dataParameters.DataParameters;
import uiTests.pageObject.AscendingOrderPricePage;
import uiTests.pageObject.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import uiTests.preconditionAction.PreconditionForTestSearchPage;

public class TestSearchResult extends BaseAbstractClass {

    SearchPage searchPage = new SearchPage();
    AscendingOrderPricePage ascendingOrderPricePage = new AscendingOrderPricePage();
    DataParameters dataParameters = new DataParameters();

    public static int count2 = 0;

    @DataProvider(name = "someData")
    public Object[][] someData() {
        return dataParameters.parametersForSearchTicket;
    }

    @Test(dataProvider = "someData", groups = "smoke")

    public void checkExistBtnBuyTicket(String cityFrom, String cityTo, int adultQuan) throws InterruptedException {

        new PreconditionForTestSearchPage().getPreparedActionForSearchPage(cityFrom, cityTo, adultQuan);

        Assert.assertTrue(searchPage.isExistValidButtonBuyTicket());
    }

    @Test(dataProvider = "someData", groups = "smoke")
    public void checkDataSearchResult(String cityFrom, String cityTo, int adultQuan) throws InterruptedException {

        new PreconditionForTestSearchPage().getPreparedActionForSearchPage(cityFrom, cityTo, adultQuan);

        Assert.assertTrue(searchPage.isAllFlightsHasValidDepartureCity(cityFrom, cityTo));
        Assert.assertTrue(searchPage.isAllFlightsHasValidDestinationCity(cityFrom, cityTo));

        Assert.assertTrue(searchPage.isAllDatesDepartValid());
        Assert.assertTrue(searchPage.isAllDatesReturnValid());
    }

    @Test(dataProvider = "someData", groups = "regression")
    public void checkOrderPrice(String cityFrom, String cityTo, int adultQuan) throws InterruptedException {

        new PreconditionForTestSearchPage().getPreparedActionForSearchPage(cityFrom, cityTo, adultQuan);

        Assert.assertTrue(ascendingOrderPricePage.isValidPriceSort());
    }
}
