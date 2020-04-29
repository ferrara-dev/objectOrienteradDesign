package model.sale;


public class Date {
    private int currentYear;
    private int monthOfTheYear;
    private int dayOfTheMonth;
    private String dayOfTheWeek;
    private String currentDate;

    public int getMonthOfTheYear() {
        return monthOfTheYear;
    }

    public int getDayOfTheMonth() {
        return dayOfTheMonth;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public int getCurrentYear() {
        return currentYear;
    }
    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public void setMonthOfTheYear(int monthOfTheYear) {
        this.monthOfTheYear = monthOfTheYear;
    }

    public void setDayOfTheMonth(int dayOfTheMonth) {
        this.dayOfTheMonth = dayOfTheMonth;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
}
