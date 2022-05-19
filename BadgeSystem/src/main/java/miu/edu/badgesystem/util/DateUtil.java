package miu.edu.badgesystem.util;

import java.time.LocalDate;
import java.time.YearMonth;


public class DateUtil {

    public static String getDayName(LocalDate localDate) {

        return localDate.getDayOfWeek().toString();
    }

    public static LocalDate getFirstDayOfMonth() {

        YearMonth month = YearMonth.now();
        return month.atDay(1);
    }

    public static LocalDate getEndDayOfMonth() {

        YearMonth month = YearMonth.now();
        return month.atEndOfMonth();
    }

}
