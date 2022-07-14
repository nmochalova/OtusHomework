package pages;

import waiters.StandartWaiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Page {
    protected WebDriver driver;
    protected StandartWaiter standartWaiter;        //явные ожидания

    public Page(WebDriver driver) {
        this.driver = driver;
        standartWaiter = new StandartWaiter(driver);
        PageFactory.initElements(driver, this);
    }
}
