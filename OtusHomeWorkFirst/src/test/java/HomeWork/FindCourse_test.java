package HomeWork;

import HomeWork.dataTable.DataTableCourse;
import HomeWork.pages.CoursePage;
import HomeWork.pages.MainPage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        String titleBeforeClick = mainPage.getNameOfCourse(course);

        Actions actions = new Actions(driver);
        actions.moveToElement(course).build().perform();

        course.click();

        CoursePage coursePage = new CoursePage(driver);
        String titleAfterClick = coursePage.getTitleByCourse(titleBeforeClick);

        //Проверяем, что открылась страница в соответствии с выбранным курсом
        System.out.println("titleBeforeClick = " + titleBeforeClick);
        System.out.println("titleAfterClick = " + titleAfterClick);

        //Проверяем, что этот курс на минимальную дату
    }

    @Test
    public void getLatestCourse() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        HashMap<WebElement, DataTableCourse> nameAndDate = mainPage.getNamesAndDates();

        WebElement course = mainPage.getMinMaxDateOfCourse(nameAndDate, "max");
        String titleBeforeClick = mainPage.getNameOfCourse(course);

        Actions actions = new Actions(driver);
        actions.moveToElement(course).build().perform();

        course.click();

        CoursePage coursePage = new CoursePage(driver);
        String titleAfterClick = coursePage.getTitleByCourse(titleBeforeClick);

        System.out.println("titleBeforeClick = " + titleBeforeClick);
        System.out.println("titleAfterClick = " + titleAfterClick);
    }

}
