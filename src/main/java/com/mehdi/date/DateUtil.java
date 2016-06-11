package com.mehdi.date;


import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;
import com.mehdi.util.icu.PersianCalendar;
import org.apache.commons.lang3.StringUtils;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.FieldPosition;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Mehdi Afsari kashi
 * @version 1.0.0
 * @since 1.0.0
 * <p/>
 * Creation Date : 2016/06/11
 */
public class DateUtil {

    public static String formatDateText(final Date dt, boolean displayDay, boolean displayDayAndTime) {
        PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        DateFormat df = pc.getDateTimeFormat(displayDay || displayDayAndTime ? java.text.DateFormat.SHORT : java.text.DateFormat.FULL, java.text.DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        System.out.println(df.format(dt));
        DateFormat df2 = pc.getDateTimeFormat(displayDay || displayDayAndTime ? java.text.DateFormat.MEDIUM : java.text.DateFormat.FULL, java.text.DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        System.out.println(df2.format(dt));
        DateFormat df3 = pc.getDateTimeFormat(displayDay || displayDayAndTime ? java.text.DateFormat.LONG : java.text.DateFormat.FULL, java.text.DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        System.out.println(df3.format(dt));
        
        
        
//          com.ibm.icu.text.DateFormat df = com.ibm.icu.text.DateFormat.getInstance();//.(DateFormat.FULL, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
//         df.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));

//         final DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

        final StringBuffer mm = new StringBuffer();
        final StringBuffer yy = new StringBuffer();
        final StringBuffer dd = new StringBuffer();
        final StringBuffer hh = new StringBuffer();
        final StringBuffer mn = new StringBuffer();
        final FieldPosition mmfp = new FieldPosition(DateFormat.MONTH_FIELD);
        final FieldPosition yyfp = new FieldPosition(DateFormat.YEAR_FIELD);
        final FieldPosition ddfp = new FieldPosition(DateFormat.DATE_FIELD);

        final FieldPosition hhfp = new FieldPosition(DateFormat.HOUR_OF_DAY0_FIELD);
        final FieldPosition mnfp = new FieldPosition(DateFormat.MINUTE_FIELD);

        df.format(dt, mm, mmfp);
        df.format(dt, yy, yyfp);
        df.format(dt, dd, ddfp);
        df.format(dt, hh, hhfp);
        df.format(dt, mn, mnfp);

        if (displayDay) {
            return yy.toString().substring(yyfp.getBeginIndex(), yyfp.getEndIndex()) + "-"
                    + mm.toString().substring(mmfp.getBeginIndex(), mmfp.getEndIndex()) + "-"
                    + dd.toString().substring(ddfp.getBeginIndex(), ddfp.getEndIndex());
        } else if (displayDayAndTime) {
            return yy.toString().substring(yyfp.getBeginIndex(), yyfp.getEndIndex()) + "-"
                    + mm.toString().substring(mmfp.getBeginIndex(), mmfp.getEndIndex()) + "-"
                    + dd.toString().substring(ddfp.getBeginIndex(), ddfp.getEndIndex()) + "-"
                    + hh.toString().substring(hhfp.getBeginIndex(), hhfp.getEndIndex()) + "-"
                    + mn.toString().substring(mnfp.getBeginIndex(), mnfp.getEndIndex());
        } else {
            return (mm.toString().substring(mmfp.getBeginIndex(), mmfp.getEndIndex())
                    + " " + yy.toString().substring(yyfp.getBeginIndex(), yyfp.getEndIndex()));
        }
    }

    public static String createDateString(XMLGregorianCalendar date) {
        PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        DateFormat df = pc.getDateTimeFormat(DateFormat.DEFAULT, DateFormat.NONE, new ULocale("fa", "IR", ""));
        DateFormat df1 = pc.getDateTimeFormat(DateFormat.NONE, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        Date time = date.toGregorianCalendar().getTime();
        Date date1 = new Date();
        date1.setTime(date.toGregorianCalendar().getTimeInMillis());//Year(time.getYear());
        date1.setMonth(time.getMonth());
        date1.setDate(time.getDate());
        date1.setHours(time.getHours());
        date1.setMinutes(time.getMinutes());

        return df.format(date1)/* + "، " + ApplicationContext.getInstance().getLtString("hour") + " " + df1.format(date.toGregorianCalendar().getTime())*/;
    }

    public static String createDateString(Long date) {
        PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        DateFormat df = pc.getDateTimeFormat(DateFormat.DEFAULT, DateFormat.NONE, new ULocale("fa", "IR", ""));
        DateFormat df1 = pc.getDateTimeFormat(DateFormat.NONE, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        Date time = new Date(date);
        Date date1 = new Date();
        date1.setTime(date);//Year(time.getYear());
        date1.setMonth(time.getMonth());
        date1.setDate(time.getDate());
        date1.setHours(time.getHours());
        date1.setMinutes(time.getMinutes());

        return df.format(date1)/* + "، " + ApplicationContext.getInstance().getLtString("hour") + " " + df1.format(date.toGregorianCalendar().getTime())*/;
    }

       public static String shamsi(Date date) {
        if (date != null) {
            PersianCalendar persianCalendar = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
            persianCalendar.setTime(date);
            final int year = persianCalendar.get(com.ibm.icu.util.Calendar.YEAR);
            final int month = persianCalendar.get(com.ibm.icu.util.Calendar.MONTH);
            final int day = persianCalendar.get(com.ibm.icu.util.Calendar.DATE);
            String format = "%4d/%02d/%02d";
            return String.format(format, year, month + 1, day);

        } else {
            return null;
        }
    }
    
    public static String createDateString(Date date) {
        if (date == null) {
            return "";
        }
        PersianCalendar persianCalendar = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
//        persianCalendar.setTime(date);
//        final int year = persianCalendar.get(com.ibm.icu.util.Calendar.YEAR);
//        final int month = persianCalendar.get(com.ibm.icu.util.Calendar.MONTH);
//        final int day = persianCalendar.get(com.ibm.icu.util.Calendar.DATE);
//        return String.format("%04d%02d%02d", year, month + 1, day);

        DateFormat df = persianCalendar.getDateTimeFormat(DateFormat.DEFAULT, DateFormat.NONE, new ULocale("fa", "IR", ""));
        return df.format(date);

//        DateFormat df = pc.getDateTimeFormat(DateFormat.DEFAULT, DateFormat.NONE, new ULocale("fa", "IR", ""));
//        return df.format(date.getTime());
    }

    public static Date convertXMLGregorianCalendartoDate(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }

    public static XMLGregorianCalendar convertDatetoXMLGregorianCalendar(Date date) {
        if (date == null) {
            return null;
        }
        try {
            GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
            gc.setTime(date);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        } catch (Exception ex) {

            return null;
        }
    }

    public static Date stringToDateConverter(String year, String month, String day, String hour, String minute) {
        try {
            if (!"".equals(year) && !"".equals(month) && !"".equals(day) && !"".equals(hour) && !"".equals(minute)) {
                return CalendarUtil.persianDateStringToDate(year + "/" + month + "/" + day + " " + hour + ":" + minute);
            } else {
                return null;
            }
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Converts a Jalali date string in (yyyy/MM/dd) format to a java.util.Date
     * instance
     *
     * @param strDate a Jalali date string
     * @return Converted Jalali date string by (yyyy/MM/dd) format to the
     * java.util.Date instance
     */
    public static Date persianDateStringToDate(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return null;
        }
        if (!strDate.equals("-") && strDate.length() >= 8) {
            String date = strDate.substring(0, 4) + "/" + strDate.substring(4, 6) + "/" + strDate.substring(6, 8);
            try {
                return persianDateStringToDate(date, CalendarUtil.DEFAULT_PERSIAN_DATE_PATTERN_WITHOUT_TIME);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static Date persianDateStringWithoutTimeToDate(String persianDateString) {
        if (StringUtils.isEmpty(persianDateString)) {
            return null;
        }
        try {
            return CalendarUtil.persianDateStringToDate(persianDateString, CalendarUtil.DEFAULT_PERSIAN_DATE_PATTERN_WITHOUT_TIME);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getDaysCount(Date begin, Date end) {
        Calendar start = org.apache.commons.lang3.time.DateUtils.toCalendar(begin);
        start.set(Calendar.MILLISECOND, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.HOUR_OF_DAY, 0);
        Calendar finish = org.apache.commons.lang3.time.DateUtils.toCalendar(end);
        finish.set(Calendar.MILLISECOND, 999);
        finish.set(Calendar.SECOND, 59);
        finish.set(Calendar.MINUTE, 59);
        finish.set(Calendar.HOUR_OF_DAY, 23);
        return (int) Math.ceil((double) (finish.getTimeInMillis() - start.getTimeInMillis()) / (1000 * 60 * 60 * 24));
    }

    public static Date getDate(String strDate) {
        if (!strDate.equals("-") && strDate.length() >= 8) {
            String date = strDate.substring(0, 4) + "/" + strDate.substring(4, 6) + "/" + strDate.substring(6, 8);
            try {
                return persianDateStringToDate(date, CalendarUtil.DEFAULT_PERSIAN_DATE_PATTERN_WITHOUT_TIME);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return null;

    }

    public static Date persianDateStringToDate(String persianDateString, String dateFormat) throws ParseException {
        TimeZone timeZone = TimeZone.getTimeZone(CalendarUtil.DEFAULT_PERSIAN_TIME_ZONE);
        ULocale uLocale = ULocale.createCanonical(CalendarUtil.DEFAULT_PERSIAN_LOCALE);
        com.ibm.icu.util.Calendar calendar = new PersianCalendar(timeZone, uLocale);
        SimpleDateFormat sdf = (SimpleDateFormat) calendar.getDateTimeFormat(DateFormat.FULL, DateFormat.FULL, uLocale);
        sdf.applyPattern(dateFormat);
        persianDateString = persianDateString.replaceAll("//", "/");
        return sdf.parse(persianDateString);
        
    }
     public static String createDateString2(Date date) {
        PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        DateFormat df = pc.getDateTimeFormat(DateFormat.DEFAULT, DateFormat.NONE, new ULocale("fa", "IR", ""));
        DateFormat df1 = pc.getDateTimeFormat(DateFormat.NONE, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        return df.format(date.getTime())/* + "، " + ApplicationContext.getInstance().getLtString("hour") + " " + df1.format(date.getTime())*/;
    }


    public static String toPersianDate(Date date, DateFormatter dateFormatter){
        if(date == null){return "";}

        String[] persianDateTokens = DateConverter.dateToStringConverter2(date);

        switch (dateFormatter){
            case YEAR_MONTH_DAY_FORWARD_SLASH:
                return String.format("%s/%s/%s", Arrays.copyOfRange(persianDateTokens, 0, 3)) ;
            case YEAR_MONTH_DAY_FORWARD_SLASH_HOUR_MIN_COLON:
                return String.format("%s/%s/%s %s:%s",Arrays.copyOfRange(persianDateTokens, 0, 5));
            case YEAR_MONTH_DAY_BACK_SLASH:
                return String.format("%s\\%s\\%s", Arrays.copyOfRange(persianDateTokens, 0, 3)) ;
            case YEAR_MONTH_DAY_BACK_SLASH_HOUR_MIN_COLON:
                return String.format("%s\\%s\\%s %s:%s",Arrays.copyOfRange(persianDateTokens, 0, 5));


        }

        return "";
    }

    
    public static void main(String[] args) throws ParseException {
        System.out.println(new Date());
        System.out.println(DateUtil.persianDateStringToDate("1393/10/13","yyyy/MM/dd"));
        
        System.out.println(DateUtil.createDateString(DateUtil.persianDateStringToDate("1393/10/13","yyyy/MM/dd")));
    }
}
