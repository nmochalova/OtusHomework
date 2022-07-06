package HomeWork;

import HomeWork.Factory.Browser;
import HomeWork.Factory.SimpleWebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ru.stqa.selenium.factory.WebDriverPool;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected EventFiringWebDriver eventDriver;

    @Before
    public void StartUp() {
        String browser = System.getProperty("browser");

        driver = new SimpleWebDriverFactory().getDriverFactory(getBrowser(browser));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        eventDriver = new EventFiringWebDriver(driver);
    }

    @After
    public void cleanUp() {
        WebDriverPool.DEFAULT.dismissAll();
    }

    private Browser getBrowser(String browser) {
        switch (browser.toLowerCase()){
            case "firefox":
                return Browser.FIREFOX;
            case "opera":
                return Browser.OPERA;
            default:
                return Browser.CHROME;
        }
    }
}
