package HomeWork.DataTable;

import lombok.Data;
import org.openqa.selenium.WebElement;

import java.util.Date;

@Data
public class DataTableCourse {
    private String name;
    private String dateString;
    private Date date;
    private WebElement element;

    public DataTableCourse(String name, String dateString, WebElement element) {
        this.name = name;
        this.dateString = dateString;
        this.element = element;
    }
}
