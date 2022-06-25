package uiTests.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import uiTests.preconditions.DateTrip;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AbstractPage {

    DateTrip dateTrip = new DateTrip();

    @FindBy(xpath = ("//div[@class='segment-route__endpoint origin']//div[@class='segment-route__city']"))
    List<WebElement> allSearchResultDeparture;

    @FindBy(xpath = ("//div[@class='segment-route__endpoint destination']//div[@class='segment-route__city']"))
    List<WebElement> allSearchResultDestination;

    @FindBy(xpath = ("//div[@class='segment-route__endpoint origin']//div[@class='segment-route__date']"))
    List<WebElement> allSearchResultDate;

    @FindBy(xpath = ("//div[@class='buy-button']//button"))
    List<WebElement> allButtonBuyTicket;

    @FindBy(xpath = ("//div[@class='buy-button']//button//div[@data-test-id='text']/div"))
    List<WebElement> allButtonBuyTicketText;

    @FindBy(xpath=("//div[@class='buy-button']"))
    WebElement buttonBuy;




    public boolean isAllFlightsHasValidDepartureCity(String cityFrom, String cityTo) throws InterruptedException {
            Thread.sleep(15000);
//        new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='segment-route__endpoint origin']//div[@class='segment-route__city']")));
//        JavascriptExecutor jse = (JavascriptExecutor)driver;
//        jse.executeScript("window.scrollBy(0,800)", "");


            List<String> listCityDepartTo = new ArrayList<>();
            List<String> listCityDepartBack = new ArrayList<>();
            for (int i = 0; i < allSearchResultDeparture.size(); i++) {
                if (i % 2 == 0)
                    listCityDepartTo.add(allSearchResultDeparture.get(i).getText());
                else listCityDepartBack.add((allSearchResultDeparture.get(i).getText()));
            }
            logger.info(listCityDepartTo + " list departure to");
            logger.info(listCityDepartBack + " list departure back");

            return (listCityDepartTo.stream().allMatch(x -> x.equals(cityFrom))
                    && listCityDepartBack.stream().allMatch(x -> x.equals(cityTo)));

    }

        public boolean isAllFlightsHasValidDestinationCity (String cityFrom, String cityTo){

            List<String> listCityDestinationTo = new ArrayList<>();
            List<String> listCityDestinationBack = new ArrayList<>();
            for (int i = 0; i < allSearchResultDestination.size(); i++) {
                if (i % 2 == 0)
                    listCityDestinationTo.add(allSearchResultDestination.get(i).getText());
                else listCityDestinationBack.add((allSearchResultDestination.get(i).getText()));
            }
            logger.info(listCityDestinationTo + " list city destination to");
            logger.info(listCityDestinationBack + " list city destination back");
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
        Thread.sleep(15000);

//       new WebDriverWait(driver, Duration.ofSeconds(20))
//                .until(ExpectedConditions.visibilityOfAllElements(allButtonBuyTicketText));
//
//        for(WebElement element: allButtonBuyTicketText){
//        JavascriptExecutor jse = (JavascriptExecutor)driver;
//        jse.executeScript("arguments[0].scrollIntoView(true);", element);}

   //     waitForElementToBeClickable(buttonBuy);

//        JavascriptExecutor jse = (JavascriptExecutor)driver;
//        jse.executeScript("window.scrollBy(0,700)", "");

        allButtonBuyTicketText.forEach(x -> logger.info(x.getText()));

       return allButtonBuyTicketText.stream().allMatch(WebElement::isEnabled) &&
                allButtonBuyTicketText.stream().allMatch(x -> x.getText().equals("Выбрать билет"));
    }
}
