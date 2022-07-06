package HomeWork;

import HomeWork.DataTable.DataTableCourse;
import HomeWork.pages.MainPage;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.*;
//mvn clean test -Dbrowser="chrome" -Dtest=FindCourse_test
public class FindCourse_test extends BaseTest{

    @Test
    public void FindCourseByName() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        List<String> names = mainPage.getNamesAllCourse();

        System.out.println("======= После фильтрации =========");
        List<String> namesAfterFilter = mainPage.filterCourseByName(names,"Engineer");
        namesAfterFilter.stream().forEach(System.out::println);
    }

    @Test
    public void getEarlyCourse() {
        eventDriver.register(new MouseListener());
        MainPage mainPage = new MainPage(eventDriver);
        mainPage.openSite();

        HashMap<WebElement, DataTableCourse> nameAndDate = mainPage.getNamesAndDates();

        WebElement course = mainPage.getMinMaxDateOfCourse(nameAndDate,"min");
        course.click();

     //   Actions actions = new Actions(eventDriver);
     //   actions.moveToElement(course).click().build().perform();
    }

    @Test
    public void getLatestCourse() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        HashMap<WebElement, DataTableCourse> nameAndDate = mainPage.getNamesAndDates();

        WebElement course = mainPage.getMinMaxDateOfCourse(nameAndDate,"max");
        course.click();
    }
}
