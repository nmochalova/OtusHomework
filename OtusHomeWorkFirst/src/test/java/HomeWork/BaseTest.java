package HomeWork;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void StartUp() {
        driver = WebDriverManager.chromedriver().create();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
