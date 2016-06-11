package com.mehdi.util;

import java.util.GregorianCalendar;

import static com.mehdi.util.PersianCalendarHelper.jp;
import static com.mehdi.util.PersianCalendarHelper.pj;
import static com.mehdi.util.PersianCalendarUtils.*;

/**
 * This class is a subclass of <code>java.util.GregorianCalendar</code>,
 * with the added functionality that it can set/get date in the Persian
 * calendar system.
 * <p/>
 * The algorithms for conversion between Persian and Gregorian calendar systems
 * are placed in <code>{@link PersianCalendarHelper}</code> class.
 *
 * @author <a href="mailto:ghasemkiani@yahoo.com">Ghasem Kiani</a>
 * @version 2.1
 */

public class SimplePersianCalendar extends GregorianCalendar implements PersianCalendarConstants {
    private static String copyright = "Copyright \u00a9 2003-2005 Ghasem Kiani <ghasemkiani@yahoo.com>. All Rights Reserved.";
    // Julian day 0, 00:00:00 hours (midnight); milliseconds since 1970-01-01 00:00:00 UTC (Gregorian Calendar)
    private static final long JULIAN_EPOCH_MILLIS = -210866803200000L;
    private static final long ONE_DAY_MILLIS = 24L * 60L * 60L * 1000L;

    /**
     * Get the Julian day corresponding to the date of this calendar.
     *
     * @return the Julian day corresponding to the date of this calendar.
     * @since 2.0
     */
    public long getJulianDay() {
        return div(getTimeInMillis() - JULIAN_EPOCH_MILLIS, ONE_DAY_MILLIS);
    }

    /**
     * Set the date of this calendar to the specified Julian day.
     *
     * @param julianDay the desired Julian day to be set as the date of this calendar.
     * @since 2.0
     */
    public void setJulianDay(long julianDay) {
        setTimeInMillis(JULIAN_EPOCH_MILLIS + julianDay * ONE_DAY_MILLIS + mod(getTimeInMillis() - JULIAN_EPOCH_MILLIS, ONE_DAY_MILLIS));
    }

    /**
     * Sets the date of this calendar object to the specified
     * Persian date (year, month, and day fields)
     *
     * @param year  the Persian year.
     * @param month the Persian month (zero-based).
     * @param day   the Persian day of month.
     * @since 1.0
     */
    public void setDateFields(int year, int month, int day) {
        setDateFields(new DateFields(year, month, day));
    }

    /**
     * Sets the date of this calendar object to the specified
     * Persian date fields
     *
     * @param dateFields the Persian date fields.
     * @since 1.0
     */
    public void setDateFields(DateFields dateFields) {
        int y = dateFields.getYear();
        int m = dateFields.getMonth();
        int d = dateFields.getDay();
        setJulianDay(pj(y > 0 ? y : y + 1, m, d));
    }

    /**
     * Retrieves the date of this calendar object as the
     * Persian date fields
     *
     * @return the date of this calendar as Persian date fields.
     * @since 1.0
     */
    public DateFields getDateFields() {
        long julianDay = getJulianDay();
        long r = jp(julianDay);
        long y = y(r);
        int m = m(r);
        int d = d(r);
        return new DateFields((int) (y > 0 ? y : y - 1), (int) m, (int) d);
    }

    /**
     * Persian month names.
     *
     * @since 1.1
     */
    public static final String[] persianMonths =
            {
                    "\u0641\u0631\u0648\u0631\u062f\u06cc\u0646",             // Farvardin
                    "\u0627\u0631\u062f\u06cc\u200c\u0628\u0647\u0634\u062a", // Ordibehesht
                    "\u062e\u0631\u062f\u0627\u062f",                         // Khordad
                    "\u062a\u06cc\u0631",                                     // Tir
                    "\u0645\u0631\u062f\u0627\u062f",                         // Mordad
                    "\u0634\u0647\u0631\u06cc\u0648\u0631",                   // Shahrivar
                    "\u0645\u0647\u0631",                                     // Mehr
                    "\u0622\u0628\u0627\u0646",                               // Aban
                    "\u0622\u0630\u0631",                                     // Azar
                    "\u062f\u06cc",                                           // Dey
                    "\u0628\u0647\u0645\u0646",                               // Bahman
                    "\u0627\u0633\u0641\u0646\u062f"                          // Esfand
            };

    /**
     * Persian week day names.
     *
     * @since 1.1
     */
    public static final String[] persianWeekDays =
            {
                    "\u0634\u0646\u0628\u0647",                         // shanbeh
                    "\u06cc\u06a9\u200c\u0634\u0646\u0628\u0647",       // yek-shanbeh
                    "\u062f\u0648\u0634\u0646\u0628\u0647",             // do-shanbeh
                    "\u0633\u0647\u200c\u0634\u0646\u0628\u0647",       // seh-shanbeh
                    "\u0686\u0647\u0627\u0631\u0634\u0646\u0628\u0647", // chahar-shanbeh
                    "\u067e\u0646\u062c\u200c\u0634\u0646\u0628\u0647", // panj-shanbeh
                    "\u062c\u0645\u0639\u0647"                          // jom'eh
            };

    /**
     * Gives the name of the specified Persian month.
     *
     * @param month the Persian month (zero-based).
     * @return the name of the specified Persian month in Persian.
     * @since 1.1
     */
    public static String getPersianMonthName(int month) {
        return persianMonths[month];
    }

    /**
     * Gives the name of the current Persian month for this calendar's date.
     *
     * @return the name of the current Persian month for this calendar's date in Persian.
     * @since 1.3
     */
    public String getPersianMonthName() {
        return getPersianMonthName(getDateFields().getMonth());
    }

    /**
     * Gives the Persian name of the specified day of week.
     *
     * @param weekDay the day of week (use symbolic constants in the <code>java.util.Calendar</code> class).
     * @return the name of the specified day of week in Persian.
     * @since 1.1
     */
    public static String getPersianWeekDayName(int weekDay) {
        switch (weekDay) {
            case SATURDAY:
                return persianWeekDays[0];
            case SUNDAY:
                return persianWeekDays[1];
            case MONDAY:
                return persianWeekDays[2];
            case TUESDAY:
                return persianWeekDays[3];
            case WEDNESDAY:
                return persianWeekDays[4];
            case THURSDAY:
                return persianWeekDays[5];
            case FRIDAY:
                return persianWeekDays[6];
        }
        return "";
    }

    /**
     * Gives the Persian name of the current day of the week for this
     * calendar's date.
     *
     * @return the name of the current day of week for this calendar's date in Persian.
     * @since 1.3
     */
    public String getPersianWeekDayName() {
        return getPersianWeekDayName(get(DAY_OF_WEEK));
    }


}
