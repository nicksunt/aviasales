package uiTests.pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AscendingOrderPricePage extends AbstractPage{

    @FindBy(xpath = ("//span[@data-test-id='price']//ancestor::div[@data-test-id='text']"))
    List<WebElement> priceList;

    @FindBy(xpath=("//div[text()='Сортировка']"))
    WebElement buttonSort;

    @FindBy(xpath=("//input[@value='cheapest']//ancestor::label//span"))
    WebElement radioBtnFirstCheapest;


    public boolean isValidPriceSort() throws InterruptedException {
        Thread.sleep(10000);


        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,500)", "");
        waitForElementToBeClickable(buttonSort);
        buttonSort.click();

        waitForElementToBeClickable(radioBtnFirstCheapest);
        radioBtnFirstCheapest.click();
        Thread.sleep(10000);

        List<Integer> listPriceParsed = new ArrayList<>();
        for (WebElement s : priceList) {
            char[] ch = s.getText().toCharArray();
            List<Integer> integerList = new ArrayList<>();
            for (char c : ch) {
                try {
                    int num = Integer.parseInt(String.valueOf(c));
                    integerList.add(num);
                } catch (Exception e) {
                    //todo log
                }
            }
            StringBuilder builder = new StringBuilder();
            for (Integer integer : integerList) {
                builder.append(integer);
            }
            listPriceParsed.add(Integer.parseInt(builder.toString()));
        }
        int minPrice = Collections.min(listPriceParsed);
        int maxPrice = Collections.max(listPriceParsed);
        Collections.sort(listPriceParsed);
        return listPriceParsed.get(0)==minPrice && listPriceParsed.get(9)==maxPrice;
    }

}
