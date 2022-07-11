package HomeWork.driver;

import HomeWork.driver.impl.ChromeWebDriver;
import HomeWork.driver.impl.FireFoxWebDriver;
import HomeWork.driver.impl.OperaWebDriver;
import HomeWork.exeptions.DriverTypeNotSupported;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverFactory implements IDriverFactory {

    @Override
    public EventFiringWebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome": {
                return new EventFiringWebDriver(new ChromeWebDriver().newDriver());
            }
            case "firefox": {
                return new EventFiringWebDriver(new FireFoxWebDriver().newDriver());
            }
            case "opera": {
                return new EventFiringWebDriver(new OperaWebDriver().newDriver());
            }
            default:
                try {
                    throw new DriverTypeNotSupported(browser);
                } catch (DriverTypeNotSupported ex) {
                    ex.printStackTrace();
                    return null;
                }
        }
    }
}
