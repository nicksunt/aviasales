package uiTests.pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import uiTests.preconditions.DateTrip;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AbstractPage {

    DateTrip dateTrip = new DateTrip();

    private final String locatorResultsDeparture = "//div[@class='segment-route__endpoint origin']//div[@class='segment-route__city']";
    @FindBy(xpath = (locatorResultsDeparture))
    List<WebElement> allSearchResultDeparture;

    private  final String locatorResultDestination = "//div[@class='segment-route__endpoint destination']//div[@class='segment-route__city']";
    @FindBy(xpath = (locatorResultDestination))
    List<WebElement> allSearchResultDestination;

    private final String locatorListSearchResult = "//div[@class='segment-route__endpoint origin']//div[@class='segment-route__date']";
    @FindBy(xpath = (locatorListSearchResult))
    List<WebElement> allSearchResultDate;

    @FindBy(xpath = ("//div[@class='buy-button']//button"))
    List<WebElement> allButtonBuyTicket;

    @FindBy(xpath = ("//div[@class='buy-button']//button//div[@data-test-id='text']/div"))
    List<WebElement> allButtonBuyTicketText;

    @FindBy(xpath = ("(//div[@class='buy-button']//button//div[@data-test-id='text']/div)[10]"))
    WebElement lastButtonBuyTicketText;



    public boolean isAllFlightsHasValidDepartureCity (String cityFrom, String cityTo) throws InterruptedException {
        Thread.sleep(5000);
      waitForElementToBeClickable(lastButtonBuyTicketText);

        List<String> listCityDepartTo = new ArrayList<>();
        List<String> listCityDepartBack = new ArrayList<>();
                  for (int i = 0; i < allSearchResultDeparture.size(); i++) {
            if (i % 2 == 0)
                listCityDepartTo.add(allSearchResultDeparture.get(i).getText());
            else listCityDepartBack.add((allSearchResultDeparture.get(i).getText()));
        }
        System.out.println(listCityDepartTo);
        System.out.println(listCityDepartBack);
        return (listCityDepartTo.stream().allMatch(x -> x.equals(cityFrom))
                && listCityDepartBack.stream().allMatch(x -> x.equals(cityTo)));
    }

    public boolean isAllFlightsHasValidDestinationCity(String cityFrom, String cityTo) throws InterruptedException {
        Thread.sleep(5000);
        waitForElementToBeClickable(lastButtonBuyTicketText);

        List<String> listCityDestinationTo = new ArrayList<>();
        List<String> listCityDestinationBack = new ArrayList<>();
                 for (int i = 0; i < allSearchResultDestination.size(); i++) {
            if (i % 2 == 0)
                listCityDestinationTo.add(allSearchResultDestination.get(i).getText());
            else listCityDestinationBack.add((allSearchResultDestination.get(i).getText()));
        }
        System.out.println(listCityDestinationTo);
        System.out.println(listCityDestinationBack);
        return (listCityDestinationTo.stream().allMatch(x -> x.equals(cityTo))
                && listCityDestinationBack.stream().allMatch(x -> x.equals(cityFrom)));
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




    public boolean isExistValidButtonBuyTicket() throws InterruptedException {
        Thread.sleep(5000);
        waitForElementToBeClickable(lastButtonBuyTicketText);


       //        return  allButtonBuyTicketText.stream().allMatch(WebElement::isDisplayed) &&
//                allButtonBuyTicketText.stream().allMatch(WebElement::isEnabled) &&
         return
                 allButtonBuyTicketText.stream().allMatch(WebElement::isDisplayed) &&
                 allButtonBuyTicketText.stream().allMatch(x -> x.getText().equals("Выбрать билет"));
    }
}
