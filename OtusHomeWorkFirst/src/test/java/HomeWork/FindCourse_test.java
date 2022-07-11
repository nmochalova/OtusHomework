package HomeWork;

import HomeWork.dataTable.DataTableCourse;
import HomeWork.pages.MainPage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.*;

//mvn clean test -Dbrowser="chrome" -Dtest=FindCourse_test
public class FindCourse_test extends BaseTest {

    @Test
    public void FindCourseByName() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        List<String> names = mainPage.getNamesAllCourse();

        System.out.println("======= После фильтрации =========");
        List<String> namesAfterFilter = mainPage.filterCourseByName(names, "Engineer");
        namesAfterFilter.stream().forEach(System.out::println);
    }

    @Test
    public void getEarlyCourse() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        HashMap<WebElement, DataTableCourse> nameAndDate = mainPage.getNamesAndDates();

        WebElement course = mainPage.getMinMaxDateOfCourse(nameAndDate, "min");

        Actions actions = new Actions(driver);
        actions.moveToElement(course).build().perform();

//        WebElement myElement = driver.findElement(By.xpath("//div[@class='container container-lessons']/div[@class='lessons']/a"));
//        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='13px solid red;'", myElement);
//        try {
//            Thread.sleep(9000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        course.click();
    }

    @Test
    public void getLatestCourse() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        HashMap<WebElement, DataTableCourse> nameAndDate = mainPage.getNamesAndDates();

        WebElement course = mainPage.getMinMaxDateOfCourse(nameAndDate, "max");

        Actions actions = new Actions(driver);
        actions.moveToElement(course).build().perform();

        course.click();
    }
}
