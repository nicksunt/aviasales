package uiTests.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.crypto.spec.PSource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AscendingOrderPricePage extends AbstractPage {

    @FindBy(xpath = ("//span[@data-test-id='price']//ancestor::div[@data-test-id='text']"))
    List<WebElement> priceList;

    @FindBy(xpath = ("//div[text()='Сортировка']"))
    WebElement buttonSort;

    @FindBy(xpath = ("//input[@value='cheapest']//ancestor::label//span"))
    WebElement radioBtnFirstCheapest;


    public boolean isValidPriceSort() throws InterruptedException {
        Thread.sleep(5000);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,500)", "");
             Thread.sleep(1000);
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Сортировка']")));
        buttonSort.click();
                 Thread.sleep(1000);
        waitForElementToBeClickable(radioBtnFirstCheapest).click();
                 Thread.sleep(3000);

        List<Integer> listPriceParsed = new ArrayList<>();
        for (WebElement s : priceList) {
            String str = s.getText();
            Pattern pattern = Pattern.compile("\\d");
            Matcher matcher = pattern.matcher(str);
            StringBuilder stringBuilder = new StringBuilder();
            while (matcher.find()) {
                stringBuilder.append(matcher.group());
            }
            listPriceParsed.add(Integer.parseInt(String.valueOf(stringBuilder)));
        }

        List<Integer> listPriceParsedAndSiteSort = new ArrayList<>(listPriceParsed);
        Collections.sort(listPriceParsed);
        return listPriceParsed.equals(listPriceParsedAndSiteSort);
    }
}