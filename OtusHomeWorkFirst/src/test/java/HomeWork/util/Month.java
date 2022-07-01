package HomeWork.util;

public enum Month {
    JANUARY("янв","01"),
    FEBRUARY("фев","02"),
    MARCH("мар","03"),
    APRIL("апр","04"),
    MAY("май","05"),
    JUNE("июн","06"),
    JULY("июл","07"),
    AUGUST("авг","08"),
    SEPTEMBER("сен","09"),
    OCTOBER("окт","10"),
    NOVEMBER("ноя","11"),
    DECEMBER("дек","12");

    private String nameRUS;
    private String numberMonth;

    Month(String nameRUS, String numberMonth) {
        this.nameRUS = nameRUS;
        this.numberMonth = numberMonth;
    }

    public String getNameRUS() {
        return nameRUS;
    }

    public String getNumberMonth() {
        return numberMonth;
    }
}
