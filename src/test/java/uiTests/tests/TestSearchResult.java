package uiTests.tests;

import org.testng.annotations.DataProvider;
import uiTests.baseAbstractClass.BaseAbstractClass;
import uiTests.dataParameters.DataParameters;
import uiTests.pageObject.AscendingOrderPricePage;
import uiTests.pageObject.AuthorizationPage;
import uiTests.pageObject.HomeAviasalesPage;
import uiTests.pageObject.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSearchResult extends BaseAbstractClass {

    SearchPage searchPage = new SearchPage();
    HomeAviasalesPage homePageAviasales = new HomeAviasalesPage();
    AscendingOrderPricePage ascendingOrderPricePage = new AscendingOrderPricePage();
    AuthorizationPage authorizationPage = new AuthorizationPage();
   // DataParameters dataParameters = new DataParameters();

    @DataProvider(name = "someData")
    public Object[][] someData(){
        return new Object[][]{
                new Object[]{DataParameters.builder()
                        .cityFrom("Москва")
                        .cityTo("Пхукет")
                        .adultQuan(2)
                        .build()},

//                new Object[]{DataParameters.builder()
//                        .cityFrom("Варшава")
//                        .cityTo("Ларнака")
//                        .adultQuan(1)
//                        .build()},
                };
        }
   //    return dataParameters.parametersForSearchTicket;


    @Test (dataProvider = "someData", groups = "SMOKE")
    public void checkDataSearchResult(DataParameters dataParameters) throws InterruptedException {  //,   String placeDepart, String placeArrival, int adultQuan) throws InterruptedException {

      //  authorizationPage.openPageHome().authorization();
        homePageAviasales.openPageHome()
                .fillDeparturePlace(dataParameters.getCityFrom())
                .fillArrivalPlace(dataParameters.getCityTo())
                .chooseDateDeparture()
                .setAdultQuantity(dataParameters.getAdultQuan()-1)
                .unChekboxGoToAnotherPage()
                .getSearchResult();

        Assert.assertTrue(searchPage.isAllFlightsHasValidDepartureCity(dataParameters.getCityFrom(), dataParameters.getCityTo()));

        Assert.assertTrue(searchPage.isAllFlightsHasValidDestinationCity(dataParameters.getCityTo(), dataParameters.getCityFrom()));



        Assert.assertTrue(searchPage.isAllDatesDepartValid());

        Assert.assertTrue(searchPage.isAllDatesReturnValid());



        Assert.assertTrue(searchPage.isExistValidButtonBuyTicket());

    }


    @Test (dataProvider = "someData")
    public void checkDataSearchResult1 (DataParameters dataParameters) throws InterruptedException {  //,   String placeDepart, String placeArrival, int adultQuan) throws InterruptedException {

        //  authorizationPage.openPageHome().authorization();
        homePageAviasales.openPageHome()
                .fillDeparturePlace(dataParameters.getCityFrom())
                .fillArrivalPlace(dataParameters.getCityTo())
                .chooseDateDeparture()
                .setAdultQuantity(dataParameters.getAdultQuan() - 1)
                .unChekboxGoToAnotherPage()
                .getSearchResult();
        Assert.assertTrue(ascendingOrderPricePage.isValidPriceSort());
    }
}
