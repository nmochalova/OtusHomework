package HomeWork;

import HomeWork.pages.MainPage;
import org.junit.Test;

import java.util.*;

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
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        HashMap<String, String> nameAndDate = mainPage.getNamesAndDates();

        System.out.println("========== Самый ранний курс: ==========");
        System.out.println(mainPage.getMinMaxDateOfCourse(nameAndDate,"min"));
    }

    @Test
    public void getLatestCourse() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        HashMap<String, String> nameAndDate = mainPage.getNamesAndDates();

        System.out.println("========== Самый поздний курс: ==========");
        System.out.println(mainPage.getMinMaxDateOfCourse(nameAndDate,"max"));
    }
}
