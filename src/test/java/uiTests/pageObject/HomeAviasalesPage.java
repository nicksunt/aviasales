package uiTests.pageObject;

import org.openqa.selenium.interactions.Actions;
import uiTests.preconditions.DateParse;
import uiTests.preconditions.DateTrip;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import uiTests.tests.TestSearchResult;

import java.util.List;


public class HomeAviasalesPage extends AbstractPage {

    @FindBy(xpath=("//input[@placeholder='Откуда']"))
     WebElement fieldDeparturePlace;

    @FindBy(xpath=("//label[@class='of_input_checkbox__label']"))
     WebElement checkBoxOstrovok;

    @FindBy(xpath=("//input[@placeholder='Куда']"))
     WebElement fieldArrivalPlace;

    @FindBy(xpath=("//input[@placeholder='Когда']"))                                              //(css =".calendar-caption__select")
     WebElement chooseMonthTo;

    @FindBy(xpath=("//input[@placeholder='Обратно']"))                                              //(css =".calendar-caption__select")
    WebElement chooseMonthFrom;


    @FindBy(xpath = ("//div[@class='calendar-day__date']"))
    List<WebElement> listDays;
///////////////
    @FindBy(xpath = ("//div[@class='calendar__month'])[2]//div[@class='calendar-day__date']"))
    List<WebElement> listDays2;

////////////////////////////
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

    public HomeAviasalesPage fillDeparturePlace(String departPlace){
        Actions actions = new Actions(driver);
        actions.click(fieldDeparturePlace).build().perform();
        fieldDeparturePlace.sendKeys(departPlace);
        return this;
    }

    public HomeAviasalesPage fillArrivalPlace(String arrivalPlace){
        fieldArrivalPlace.sendKeys(arrivalPlace);
        return this;
    }

    public HomeAviasalesPage unChekboxGoToAnotherPage(){
        TestSearchResult testSearchResult = new TestSearchResult();
        if(!checkBoxOstrovok.isSelected())
            checkBoxOstrovok.click();
        return this;
    }

    public HomeAviasalesPage chooseDateDeparture() throws InterruptedException {

        DateTrip dateTrip = new DateTrip();
        String monthDepart = DateParse.getMonth(dateTrip.monthNumberDeparture);
        String monthReturn = DateParse.getMonth(dateTrip.monthNumberReturn);
        String numDayDepart = dateTrip.stringNumDayDepart;
        String numDayReturn = dateTrip.stringNumDayReturn;


        Actions action = new Actions(driver);
        action.moveToElement(chooseMonthTo).click().build().perform();
       // chooseMonthTo.click();
        WebElement element = calendar.stream().filter(x -> x.getText().contains(monthDepart)).findFirst().get();
        element.click();
        Thread.sleep(1000);
        WebElement dayNumDepart = listDays.stream().filter(x -> x.getText().equals(numDayDepart)).findFirst().get();
        dayNumDepart.click();

      //   chooseMonthFrom.click();
        action.moveToElement(chooseMonthFrom).click().build().perform();
        WebElement element1 = calendar.stream().filter(x -> x.getText().contains(monthReturn)).findFirst().get();
        element1.click();
        WebElement dayNumReturn = listDays.stream().filter(x -> x.getText().equals(numDayReturn)).findFirst().get();
        dayNumReturn.click();
        return this;
    }


    public HomeAviasalesPage setAdultQuantity(int adultQuan){
        passengersQuantity.click();
        while (adultQuan!=0) {
            adultQuantity.click();
            adultQuan--;
        }
        return this;
    }



    public SearchPage getSearchResult(){
        buttonSubmit.click();
        return new SearchPage();
    }
}
