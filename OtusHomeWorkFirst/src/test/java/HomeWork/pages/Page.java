package HomeWork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Page {
    protected WebDriver driver;
    public WebDriverWait wait;
    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);   //явные ожидания
        PageFactory.initElements(driver, this);
    }

    public boolean isElementPresent(By locator) {
        try {
            wait.until(presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }


}
