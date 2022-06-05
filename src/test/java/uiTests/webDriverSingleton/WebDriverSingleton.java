package uiTests.webDriverSingleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSingleton {

    private static WebDriver webDriver;

    private WebDriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (webDriver == null) {
            webDriver = new ChromeDriver();
          //  webDriver = new FirefoxDriver();
        }
        return webDriver;
    }

    public static void closeDriver() throws InterruptedException {
        webDriver.quit();
        webDriver = null;

    }
}


