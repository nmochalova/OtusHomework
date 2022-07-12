package HomeWork;

import HomeWork.driver.DriverFactory;
import HomeWork.listener.MouseListener;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected EventFiringWebDriver driver;

    @Before
    public void StartUp() {
        String browser = System.getProperty("browser");

        driver = new DriverFactory().getDriver(browser);
        driver.register(new MouseListener());                                //регистрируем прослушиватель для драйвера
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //неявные ожидания (ожидание появление эл-та в DOM средствами браузера)
        driver.manage().window().maximize();
    }

    @After
    public void cleanUp() {
            driver.close();
            driver.quit();
            driver = null;
    }
}
