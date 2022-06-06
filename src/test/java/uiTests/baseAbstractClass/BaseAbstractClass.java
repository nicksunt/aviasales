package uiTests.baseAbstractClass;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import uiTests.webDriverSingleton.WebDriverSingleton;

public class BaseAbstractClass {

    @BeforeTest
    public  void openChrome() {
        WebDriverSingleton.getDriver().manage().window().maximize();
    }

    @AfterTest
    public  void tearDown() throws InterruptedException {
        WebDriverSingleton.closeDriver();

    }
}

