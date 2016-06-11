package com.mehdi.date;

import com.mehdi.util.icu.PersianCalendar;

import java.text.ParseException;
import java.util.Date;

/**
 * @author Mehdi Afsari kashi
 * @version 1.0.0
 * @since 1.0.0
 * <p/>
 * Creation Date : 2015/11/25
 */
public class DateConverter {

    private static final Date NULL_DATE = null;// new Date();

    /*
    * return date of birth on this format : [shamsi date] YYYYmm/dd
    */
    public static String dateToStringConverter(Date date, String delimeter) {
        if (date != null) {
            PersianCalendar pc = new PersianCalendar(date);

            long year = pc.get(PersianCalendar.EXTENDED_YEAR);
            int month = pc.get(PersianCalendar.MONTH) + 1;
            int day = pc.get(PersianCalendar.DAY_OF_MONTH);
            return String.valueOf(year) + delimeter + String.format("%02d", month) + delimeter + String.valueOf(day);
        } else {
            return null;
        }
    }

    public static Date stringToDateConverter(String year, String month, String day, String hour, String minute) {
        try {
            if (!"".equals(year) && !"".equals(month) && !"".equals(day) && !"".equals(hour) && !"".equals(minute)) {
                return CalendarUtil.persianDateStringToDate(year + "/" + month + "/" + day + " " + hour + ":" + minute);
            } else {
                return NULL_DATE;
            }
        } catch (ParseException e) {
            return NULL_DATE;
        }
    }

    public static Date stringToDateConverterWithoutTime(String year, String month, String day) {
        try {
            if (year != null && month != null && day != null) {
                return CalendarUtil.persianDateStringToDate(year + "/" + month + "/" + day);
            } else {
                return NULL_DATE;
            }
        } catch (ParseException e) {
            return NULL_DATE;
        }
    }

    /** 
     * in order : year, month, day, hour, minute
     * @param date
     * @return
     */
    public static String[] dateToStringConverter2(Date date) {
        return DateUtil.formatDateText(date, false, true).split("-");
    }

}
