package uiTests.preconditionAction;

import uiTests.pageObject.HomeAviasalesPage;

public class PreconditionForTestSearchPage {

    public void getPreparedActionForSearchPage(String cityFrom,String cityTo, int adultQuan) throws InterruptedException {
    //  authorizationPage.openPageHome().authorization();
        new HomeAviasalesPage().openPageHome()
                .fillDeparturePlace(cityFrom)
                .fillArrivalPlace(cityTo)
                .chooseDateDeparture()
                .setAdultQuantity(adultQuan - 1)
                .unChekboxGoToAnotherPage()
                .getSearchResult();
    }
}
