package HomeWork;

import HomeWork.driver.DriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected EventFiringWebDriver eventDriver;

    @Before
    public void StartUp() {
        String browser = System.getProperty("browser");

        driver = new DriverFactory().getDriver(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        eventDriver = new EventFiringWebDriver(driver);
    }

    @After
    public void cleanUp() {
            driver.quit();
            driver = null;
    }
}
