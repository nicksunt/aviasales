package uiTests.pageObject;

import uiTests.preconditions.DateTrip;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AbstractPage {

    DateTrip dateTrip = new DateTrip();

    @FindBy(xpath = ("//div[@class='segment-route__endpoint origin']//div[@class='segment-route__city']"))
    List<WebElement> allSearchResultDeparture;

    @FindBy(xpath = ("//div[@class='segment-route__endpoint destination']//div[@class='segment-route__city']"))
    List<WebElement> allSearchResultDestination;

    private final String locatorListSearchResult = "//div[@class='segment-route__endpoint origin']//div[@class='segment-route__date']";
    @FindBy(xpath = (locatorListSearchResult))
    List<WebElement> allSearchResultDate;

    @FindBy(xpath = ("//div[@class='buy-button']//button"))
    List<WebElement> allButtonBuyTicket;

    @FindBy(xpath = ("//div[@class='buy-button']//button//div[@data-test-id='text']/div"))
    List<WebElement> allButtonBuyTicketText;


    public boolean isAllFlightsHasValidDepartureCity(String cityFrom, String cityTo) throws InterruptedException {
        //  waitForPresenceOfAllElementsLocatedBy(locatorListSearchResult);
        List<String> cityDepartureToList = new ArrayList<>();
        List<String> cityDepartureFromList = new ArrayList<>();
        Thread.sleep(2000);
        for (int i = 0; i < allSearchResultDeparture.size(); i++) {
            if (i % 2 == 0)
                cityDepartureToList.add(allSearchResultDeparture.get(i).getText());
            else cityDepartureFromList.add((allSearchResultDeparture.get(i).getText()));
        }
        return (cityDepartureToList.stream().allMatch(x -> x.equals(cityFrom))
                && cityDepartureFromList.stream().allMatch(x -> x.equals(cityTo)));
    }

    public boolean isAllFlightsHasValidDestinationCity(String cityFrom, String cityTo) {
        List<String> listCityDestinationTo = new ArrayList<>();
        List<String> listCityDestinationFrom = new ArrayList<>();
        for (int i = 0; i < allSearchResultDestination.size(); i++) {
            if (i % 2 == 0)
                listCityDestinationTo.add(allSearchResultDestination.get(i).getText());
            else listCityDestinationFrom.add((allSearchResultDestination.get(i).getText()));
        }
        return (listCityDestinationTo.stream().allMatch(x -> x.equals("Пхукет"))
                && listCityDestinationFrom.stream().allMatch(x -> x.equals("Москва")));
    }

    public boolean isAllDatesDepartValid() {

        String validDateDepartTo = dateTrip.fullDateDepart;
        List<String> listDateDepart = new ArrayList<>();

        for (int i = 0; i < allSearchResultDate.size(); i++) {
            if (i % 2 == 0)
                listDateDepart.add(allSearchResultDate.get(i).getText());
        }
        return (listDateDepart.stream().allMatch(x -> x.contains(validDateDepartTo)));
    }

    public boolean isAllDatesReturnValid() {
        String validDateReturn = dateTrip.fullDateReturn;
        List<String> listDayReturn = new ArrayList<>();

        for (int i = 0; i < allSearchResultDate.size(); i++) {
            if (i % 2 != 0)
                listDayReturn.add(allSearchResultDate.get(i).getText());
        }
        return listDayReturn.stream().allMatch(x -> x.contains(validDateReturn));
    }

    public boolean isExistValidButtonBuyTicket() {
        return allButtonBuyTicket.size() ==10 &&
                allButtonBuyTicket.stream().allMatch(WebElement::isDisplayed) &&
                allButtonBuyTicket.stream().allMatch(WebElement::isEnabled) &&
                allButtonBuyTicketText.stream().allMatch(x -> x.getText().equals("Выбрать билет"));
    }
}
