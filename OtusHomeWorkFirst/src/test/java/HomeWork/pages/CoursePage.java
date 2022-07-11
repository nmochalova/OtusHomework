package HomeWork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CoursePage extends Page{
    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public String getTitleByCourse(String titleBeforeClick) {
        By locator;

        //Локаторы для разных курсов различаются
        if (!titleBeforeClick.contains("Специализация"))
             locator = By.cssSelector(".course-header2__title");
         else
             locator = By.cssSelector(".tn-atom[field=tn_text_1613574457579]");

        isElementPresent(locator);

       return driver.findElement(locator).getAttribute("innerText");
    }

}
