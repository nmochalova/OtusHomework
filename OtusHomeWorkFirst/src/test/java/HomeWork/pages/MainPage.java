package HomeWork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MainPage {
    private WebDriver driver;
    private final String SITE = "https://otus.ru";

    @FindBy(xpath = "//div[@class='container container-lessons']/div[@class='lessons']")
    private WebElement popularCourses;

    @FindBy(xpath = "//div[@class='container-padding-bottom']/div[@class='lessons']")
    private WebElement specializationsCourses;

    @FindBy(xpath = "//div[@class='lessons']//a[contains(@class,'lessons__new-item')]")
    private List<WebElement> allCourses;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openSite() {
        driver.get(SITE);
    }

    public List<String> getNamesAllCourse() {
        List<String> names = new ArrayList<>();
        for (WebElement element : allCourses) {
            names.add(element.findElement(By.xpath(".//div[contains(@class,'lessons__new-item-title')]")).getText());
        }
        return names;
    }

    public HashMap<String, String> getNamesAndDates() {
        HashMap<String, String> nameAndDate = new HashMap<>();
        String nameCourse, dateCourse;

        List<WebElement> blockPopular = popularCourses.findElements(By.tagName("a"));
        for (WebElement element : blockPopular) {
            nameCourse = element
                    .findElement(By.xpath(".//div[contains(@class,'lessons__new-item-title')]"))
                    .getText();
            dateCourse = element
                    .findElement(By.xpath(".//div[@class='lessons__new-item-start']"))
                    .getText();
            nameAndDate.put(nameCourse, dateCourse);
        }

        List<WebElement> blockSpecial = specializationsCourses.findElements(By.tagName("a"));
        for (WebElement element : blockSpecial) {
            nameCourse = element
                    .findElement(By.xpath(".//div[contains(@class,'lessons__new-item-title')]"))
                    .getText();
            dateCourse = element
                    .findElement(By.xpath(".//div[@class='lessons__new-item-time']"))
                    .getText();
            nameAndDate.put(nameCourse, dateCourse);
        }

        return nameAndDate;
    }

    public List<String> filterCourseByName(List<String> names, String name) {
        return names.stream().filter(p -> p.contains(name)).collect(Collectors.toList());
    }

//    public Date getMinDateofCourse(HashMap<String, String> nameAndDate) {
//        HashMap<String, Date> namesAndDateFormat = new HashMap<>();
//
//        //Парсим строку в массив дат
//        HashMap<Integer, String> parseDate = parseDateToString(nameAndDate);
//        //Преобразование строки в дату
//        List<Date> datesCourse = StringToDate(parseDate);
//
//        return datesCourse.stream().min(Date::compareTo).get();
//    }

    public List<String> getMaxDateOfCourse(HashMap<String, String> nameAndDate) {
        HashMap<String, Date> namesAndDateFormat = new HashMap<>();

        for(Map.Entry<String,String> entry : nameAndDate.entrySet()) {
            Date dt = parserDate(entry.getValue());
            if (dt != null) {
                namesAndDateFormat.put(entry.getKey(), parserDate(entry.getValue()));
            }
        }

        Date maxDate = namesAndDateFormat.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getValue();

        List<String> result = namesAndDateFormat.entrySet().stream()
                .filter(p -> p.getValue().equals(maxDate))
                .map(p -> p.getKey())
                .collect(Collectors.toList());

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
    public Date StringToDate(int day, String month) {
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

    public String getMonth(String month) {
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
