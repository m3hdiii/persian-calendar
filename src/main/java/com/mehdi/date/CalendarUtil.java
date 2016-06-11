package com.mehdi.date;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;
import com.mehdi.util.icu.PersianCalendar;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * @author Mehdi Afsari kashi
 * @version 1.0.0
 * @since 1.0.0
 * <p/>
 * Creation Date : 2015/11/25
 */
public final class CalendarUtil {

    public static final String DEFAULT_PERSIAN_DATE_PATTERN = "yyyy/MM/dd hh:mm";//:mm a";
//    public static final String DEFAULT_PERSIAN_DATE_PATTERN = "yyyy/MM/dd";
    public static final String DEFAULT_PERSIAN_DATE_PATTERN_WITHOUT_TIME = "yyyy/MM/dd";
    public static final String DEFAULT_PERSIAN_TIME_ZONE = "Asia/Tehran";
    public static final String DEFAULT_PERSIAN_LOCALE = "fa_IR";

    private CalendarUtil() {
    }

    /**
     * This method converts a data-time (java.util.Date) instance to a
     *  the localized calendar date string which is set by passed
     * locale 
     *
     * @param time
     * @return returns
     */
    public static String toDateString(Date time, Locale locale) {
        DateFormat df;
        if (locale != null && locale.getLanguage().contains("fa")) {
            TimeZone timeZone = TimeZone.getTimeZone(DEFAULT_PERSIAN_TIME_ZONE);
            ULocale uLocale = ULocale.createCanonical(DEFAULT_PERSIAN_LOCALE);
            Calendar calendar = new PersianCalendar(timeZone, uLocale);

            df = calendar.getDateTimeFormat(DateFormat.FULL, DateFormat.FULL, uLocale);
            ((SimpleDateFormat) df).applyPattern(DEFAULT_PERSIAN_DATE_PATTERN);

        } else {
            df = Calendar.getInstance().getDateTimeFormat(DateFormat.FULL, DateFormat.FULL, locale);
        }
        return df.format(time);
    }

    /**
     * This method converts a data-time (java.util.Date) instance to a
     *  the localized calendar date string which is set by passed
     * locale
     *
     * @param time
     *  @return returns
     */
    public static String toDateString(Date time) {
        return toDateString(time, Locale.getDefault());
    }

    /**
     * This method converts a data-time long value to a
     * current localized calendar date string
     *
     *
     * @param time The long date-time value
     * @return The converted date string
     */
    public static String toDateString(long time) {
        return toDateString(new Date(time));
    }

    public static Date dateStringToDate(String dateString) throws ParseException {
        return dateStringToDate(dateString, Locale.getDefault());
    }

    public static Date dateStringToDate(String dateString, Locale locale) throws ParseException {
        return dateStringToDate(dateString, DEFAULT_PERSIAN_DATE_PATTERN, locale);
    }

    public static Date dateStringToDate(String dateString, String dateFormat, Locale locale) throws ParseException {
        if (locale != null && locale.getLanguage().contains("fa")) {
            return persianDateStringToDate(dateString, dateFormat);
        } else {
            return commonDateStringToDate(dateString, dateFormat);
        }
    }

    /**
     * Converts a Jalali date string in any format format to a java.util.Date
     * instance
     *
     * @param dateString a localized date string
     * @return Converted localized date string in any format format to the
     *         java.util.Date instance
     */
    public static Date commonDateStringToDate(String dateString, String dateFormat) throws ParseException {
        SimpleDateFormat sdf = (SimpleDateFormat) Calendar.getInstance().getDateTimeFormat(DateFormat.FULL, DateFormat.FULL, Locale.getDefault());
        sdf.applyPattern(dateFormat);
        return sdf.parse(dateString);
    }

    /**
     * Converts a Jalali date string in (yyyy/MM/dd) format to a java.util.Date
     * instance
     *
     * @param persianDateString a Jalali date string
     * @return Converted Jalali date string by (yyyy/MM/dd) format to the
     *         java.util.Date instance
     */
    public static Date persianDateStringToDate(String persianDateString, String dateFormat) throws ParseException {
        TimeZone timeZone = TimeZone.getTimeZone(DEFAULT_PERSIAN_TIME_ZONE);
        ULocale uLocale = ULocale.createCanonical(DEFAULT_PERSIAN_LOCALE);
        Calendar calendar = new PersianCalendar(timeZone, uLocale);
        SimpleDateFormat sdf = (SimpleDateFormat) calendar.getDateTimeFormat(DateFormat.FULL, DateFormat.FULL, uLocale);
        sdf.applyPattern(dateFormat);
        return sdf.parse(persianDateString);
    }

    /**
     * Converts a Jalali date string in (yyyy/MM/dd) format to a java.util.Date
     * instance
     *
     * @param persianDateString a Jalali date string
     * @return Converted Jalali date string by (yyyy/MM/dd) format to the
     *         java.util.Date instance
     */
    public static Date persianDateStringToDate(String persianDateString) throws ParseException {
        return persianDateStringToDate(persianDateString, DEFAULT_PERSIAN_DATE_PATTERN);
    }

    /**
     * This function accept three parameters of a Jalali (Shamsi) date
     * which are year, month and day and converts the passed date to
     * the system java.util.Date instance
     *
     * @param year The Jalali (Shamsi) year value
     * @param month The Jalali (Shamsi) month of year value (1-12)
     * @param day The Jalali (Shamsi) day of month value (1-31)
     * @return The converted system java.util.Date instance
     */
    public static Date persianDateStringToDate(int year, int month, int day) throws ParseException {
        return persianDateStringToDate(year + "/" + month + "/" + day);
    }

    /**
     * Returns the current date in Jalali (Shamsi) date string in
     * (yyyy/MM/dd) format
     *
     *  @return returns 
     */
    public static String currentPersianDate() {
        return toDateString(System.currentTimeMillis());
    }
}
