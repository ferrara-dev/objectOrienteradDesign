package util;

import model.sale.Date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

public class Calendar {
    static private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    static private LocalDateTime now;

    public static String getTimeAndDate() {
        now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static Date getDate() {
        Date date = new Date();
        date.setCurrentDate(getTimeAndDate());
        date.setCurrentYear(getYear());
        date.setDayOfTheMonth(getMonthOfYear());
        date.setDayOfTheMonth(getDayOfMonth());
        date.setDayOfTheWeek(getDayOfTheWeek());
        return date;
    }

    public static int getYear() {
        LocalDate now = LocalDate.now();
        return now.getYear();
    }

    public static int getMonthOfYear() {
        return LocalDate.now().getMonth().getValue();
    }

    public static int getDayOfMonth() {
        return LocalDate.now().getDayOfMonth();
    }

    public static String getDayOfTheWeek() {
        now = LocalDateTime.now();

        return DayOfWeek.from(now).name();
    }

    public static boolean compareDates(Date date1, Date date2, boolean exact) {
        if (date1 == null && date2 == null)
            throw new IllegalArgumentException();
        if (exact) {
            if (date1.getCurrentDate() == date2.getCurrentDate())
                return true;
        } else if (!exact) {
            if (date1.getDayOfTheMonth() == date2.getDayOfTheMonth())
                if (date1.getCurrentYear() == date2.getCurrentYear())
                    if (date1.getMonthOfTheYear() == date2.getMonthOfTheYear())
                        return true;
        }
        return false;
    }
}
