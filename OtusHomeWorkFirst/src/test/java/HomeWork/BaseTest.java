package HomeWork;

import HomeWork.driver.DriverFactory;
import HomeWork.listener.MouseListener;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import java.util.concurrent.TimeUnit;

public class BaseTest {
 //   protected WebDriver driver;
    protected EventFiringWebDriver driver;

    @Before
    public void StartUp() {
        String browser = System.getProperty("browser");

        driver = new DriverFactory().getDriver(browser);
        driver.register(new MouseListener());                 //регистрируем прослушиватель для драйвера
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void cleanUp() {
            driver.quit();
            driver = null;
    }
}
