package HomeWork.Factory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import ru.stqa.selenium.factory.WebDriverPool;

public class SimpleWebDriverFactory {

public WebDriver getDriverFactory(Browser browser) {
        WebDriver driver = null;

        switch (browser){
            case CHROME:
                driver = WebDriverPool.DEFAULT.getDriver(new ChromeOptions());
                break;
            case FIREFOX:
                driver = WebDriverPool.DEFAULT.getDriver(new FirefoxOptions());
                break;
            case OPERA: {
                driver = WebDriverPool.DEFAULT.getDriver(new OperaOptions());
                break;
            }
        }
        return driver;
}
}
