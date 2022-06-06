package uiTests.pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import uiTests.preconditions.DateParse;
import uiTests.preconditions.DateTrip;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import uiTests.tests.TestSearchResult;

import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class HomeAviasalesPage extends AbstractPage {

    @FindBy(xpath=("//input[@placeholder='Откуда']"))
     WebElement fieldDeparturePlace;

    @FindBy(xpath=("//label[@class='of_input_checkbox__label']"))
     WebElement checkBoxOstrovok;

    @FindBy(xpath=("//input[@placeholder='Куда']"))
     WebElement fieldArrivalPlace;

    @FindBy(xpath=("//input[@placeholder='Куда']/.."))
    WebElement forEmptyFieldArrivalPlace;


    @FindBy(xpath=("//input[@placeholder='Когда']"))
     WebElement chooseMonthTo;

    @FindBy(xpath=("//input[@placeholder='Когда']/../.."))
    WebElement forEmptyFieldDepartDate;

    @FindBy(xpath=("//input[@placeholder='Обратно']"))
    WebElement chooseMonthFrom;


    @FindBy(xpath = ("//div[@class='calendar-day__date']"))
    List<WebElement> listDays;

    @FindBy(xpath=("//button[@type='submit']"))
    WebElement buttonSubmit;

    @FindBy(xpath=("//div[@data-test-id='passengers-field']"))
     WebElement passengersQuantity;

    @FindBy(xpath=("//div[@data-test-id='passengers-adults-field']//a[@class='additional-fields__passenger-control --increment']"))
     WebElement adultQuantity;

    @FindBy(css =".calendar-caption__select option")
    List<WebElement> calendar;

    public HomeAviasalesPage openPageHome() {
        driver.get(URL_HOMEPAGE);
        return this;
    }

    public HomeAviasalesPage fillDeparturePlace(String departPlace) throws InterruptedException {
        Thread.sleep(1000);
        Actions action = new Actions(driver);
        action.click(fieldDeparturePlace).build().perform();
        action.sendKeys(Keys.DELETE).build().perform();
        Thread.sleep(500);
        fieldDeparturePlace.sendKeys(departPlace);
        return this;
    }

    public HomeAviasalesPage fillArrivalPlace(String arrivalPlace) throws InterruptedException {
        fieldArrivalPlace.sendKeys(arrivalPlace);
        return this;
    }

    public HomeAviasalesPage unChekboxGoToAnotherPage(){
        int count1 = 1;
        TestSearchResult.count2 += count1;
        if(TestSearchResult.count2<2)
            checkBoxOstrovok.click();
        return this;
    }


    public HomeAviasalesPage chooseDateDeparture() throws InterruptedException {

        DateTrip dateTrip = new DateTrip();
        String monthDepart = DateParse.getMonth(dateTrip.monthNumberDeparture);
        String monthReturn = DateParse.getMonth(dateTrip.monthNumberReturn);
        String numDayDepart = dateTrip.stringNumDayDepart;
        String numDayReturn = dateTrip.stringNumDayReturn;
        logger.info(numDayDepart + " " + monthDepart + " by " +numDayReturn + " " +  monthReturn );


        Actions action = new Actions(driver);
        action.moveToElement(chooseMonthTo).click().build().perform();

        WebElement element = calendar.stream().filter(x -> x.getText().contains(monthDepart)).findFirst().get();
        element.click();
        Thread.sleep(2000);
        WebElement dayNumDepart = listDays.stream().filter(x -> x.getText().equals(numDayDepart)).findFirst().get();
        dayNumDepart.click();

        action.moveToElement(chooseMonthFrom).click().build().perform();
        WebElement element1 = calendar.stream().filter(x -> x.getText().contains(monthReturn)).findFirst().get();
        element1.click();
        Thread.sleep(2000);
        WebElement dayNumReturn = listDays.stream().filter(x -> x.getText().equals(numDayReturn)).findFirst().get();
        dayNumReturn.click();
        return this;
    }

    public String getTextForEmptyFieldArrivalPlace(){
       return (forEmptyFieldArrivalPlace.getAttribute("data-error-message"));
    }

    public String getTextForEmptyFieldDate(){
        return (forEmptyFieldDepartDate.getAttribute("data-error-message"));
    }

    public HomeAviasalesPage setAdultQuantity(int adultQuan){
        passengersQuantity.click();
        while (adultQuan!=0) {
            adultQuantity.click();
            adultQuan--;
        }
        return this;
    }

    public SearchPage getSearchResult() throws InterruptedException {
        waitForElementToBeClickable(buttonSubmit);
        buttonSubmit.click();
        return new SearchPage();
    }
}
