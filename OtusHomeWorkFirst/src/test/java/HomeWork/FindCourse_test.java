package HomeWork;

import HomeWork.pages.MainPage;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

public class FindCourse_test extends BaseTest{

    @Test
    public void FindCourseByName() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        List<String> names = mainPage.getNamesAllCourse();
        names.stream().forEach(System.out::println);

        System.out.println("======= После фильтрации =========");
        List<String> namesAfterFilter = mainPage.filterCourseByName(names,"Engineer");
        namesAfterFilter.stream().forEach(System.out::println);
    }

    @Test
    public void getEarlyCourse() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        HashMap<String, String> nameAndDate = mainPage.getNamesAndDates();
        for (Map.Entry str : nameAndDate.entrySet()) {
            System.out.println(str.getKey() + " : " + str.getValue());
        }

        System.out.println("========== Самый поздний курс: ==========");
        mainPage.getMaxDateOfCourse(nameAndDate).stream().forEach(System.out::println);
    }
}
