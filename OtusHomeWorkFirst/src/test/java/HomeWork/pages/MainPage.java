package HomeWork.pages;

import HomeWork.DataTable.DataTableCourse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MainPage extends Page{
    public MainPage(WebDriver driver) {
        super(driver);
    }
    private final String SITE = "https://otus.ru";
    public void openSite() {
        driver.get(SITE);
    }

    @FindBy(xpath = "//div[@class='container container-lessons']/div[@class='lessons']")
    private WebElement popularCourses;

    @FindBy(xpath = "//div[@class='container-padding-bottom']/div[@class='lessons']")
    private WebElement specializationsCourses;

    @FindBy(xpath = "//div[@class='lessons']//a[contains(@class,'lessons__new-item')]")
    private List<WebElement> allCourses;

    public List<String> getNamesAllCourse() {
        List<String> names = new ArrayList<>();
        for (WebElement element : allCourses) {
            names.add(element.findElement(By.xpath(".//div[contains(@class,'lessons__new-item-title')]")).getText());
        }
        return names;
    }

    public List<DataTableCourse> getNamesAndDates() {
        List<DataTableCourse> nameAndDate = new ArrayList<>();
        String nameCourse, dateCourse;

        List<WebElement> blockPopular = popularCourses.findElements(By.tagName("a"));
        for (WebElement element : blockPopular) {
            nameCourse = element
                    .findElement(By.xpath(".//div[contains(@class,'lessons__new-item-title')]"))
                    .getText();
            dateCourse = element
                    .findElement(By.xpath(".//div[@class='lessons__new-item-start']"))
                    .getText();
            nameAndDate.add(new DataTableCourse(nameCourse, dateCourse, element));
        }

        List<WebElement> blockSpecial = specializationsCourses.findElements(By.tagName("a"));
        for (WebElement element : blockSpecial) {
            nameCourse = element
                    .findElement(By.xpath(".//div[contains(@class,'lessons__new-item-title')]"))
                    .getText();
            dateCourse = element
                    .findElement(By.xpath(".//div[@class='lessons__new-item-time']"))
                    .getText();
            nameAndDate.add(new DataTableCourse(nameCourse, dateCourse, element));
        }

        return nameAndDate;
    }

    //Метод фильтр по названию курса
    public List<String> filterCourseByName(List<String> names, String name) {
        return names.stream().filter(p -> p.contains(name)).collect(Collectors.toList());
    }

    //Метод выбора курса, стартующего раньше всех/позже всех (при совпадении дат - выбрать любой) при помощи reduce
    //flag принимает значение "max" - для выбора курса, стартующего позже всех и "min" - раньше всех.
    public WebElement getMinMaxDateOfCourse(List<DataTableCourse> nameAndDate, String flag) {

        for(DataTableCourse entry : nameAndDate) {
            Date dt = parserDate(entry.getDateString());
            if (dt != null) {
                entry.setDate(dt);
            }
        }

        WebElement result = null;
        if (flag.equals("max")) {
            result = nameAndDate.stream()
                    .filter(p -> p.getDate()!=null)
                    .reduce((s1, s2) -> (s1.getDate().after(s2.getDate()) ? s1 : s2))
                    .map(DataTableCourse::getElement)
                    .get();
        } else if (flag.equals("min")) {
            result = nameAndDate.stream()
                    .filter(p -> p.getDate()!=null)
                    .reduce((s1,s2) -> (s1.getDate().before(s2.getDate()) ? s1 : s2))
                    .map(DataTableCourse::getElement)
                    .get();
        }
        System.out.println("Выбран курс: " + result.getText());
        return result;
    }

    //Парсим строку в массив дат
    private Date parserDate(String StringDateFromSite) {
        int day;
        String month;
        if (!StringDateFromSite.equals("О дате старта будет объявлено позже")) {
            String[] paramAndValue = StringDateFromSite.split(" ");
            try {
                day = Integer.parseInt(paramAndValue[0]);
                month = paramAndValue[1];
            } catch (NumberFormatException e) {
                day = Integer.parseInt(paramAndValue[1]);
                month = paramAndValue[2];
            }
            return StringToDate(day, month);
        } else
            return null;
    }

    //Преобразование строки в дату
    private Date StringToDate(int day, String month) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        String monthNumber = getMonth(month);
        try {
            String str = String.format("%d/%s/%d", day, monthNumber, year);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            return formatter.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String getMonth(String month) {
        String monthRUS = String.valueOf(month.toCharArray(), 0, 3);
        switch (monthRUS) {
            case "янв":
                return "01";
            case "фев":
                return "02";
            case "мар":
                return "03";
            case "апр":
                return "04";
            case "май":
                return "05";
            case "июн":
                return "06";
            case "июл":
                return "07";
            case "авг":
                return "08";
            case "сен":
                return "09";
            case "окт":
                return "10";
            case "ноя":
                return "11";
            case "дек":
                return "12";
            default:
                return null;
        }
    }
}
