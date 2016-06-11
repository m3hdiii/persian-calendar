package com.mehdi.util.icu;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.ULocale;

import java.util.Locale;

public class PersianDateFormat extends SimpleDateFormat {
    /**
     * Creates a <code>PersianDateFormat</code> with the default pattern and locale.
     */
    public PersianDateFormat() {
        this("yyyy/MM/dd G HH:mm:ss z", ULocale.getDefault());
    }

    /**
     * Creates a <code>PersianDateFormat</code> with the specified pattern and the
     * default locale.
     *
     * @param pattern the pattern to be used by this object.
     */
    public PersianDateFormat(String pattern) {
        this(pattern, ULocale.getDefault());
    }

    /**
     * Creates a <code>PersianDateFormat</code> with the specified pattern and locale.
     *
     * @param pattern the pattern to be used by this object.
     * @param loc     the provided locale for this object.
     */
    public PersianDateFormat(String pattern, Locale loc) {
        this(pattern, ULocale.forLocale(loc));
    }

    /**
     * Creates a <code>PersianDateFormat</code> with the specified pattern and locale.
     *
     * @param pattern the pattern to be used by this object.
     * @param loc     the provided locale for this object.
     */
    public PersianDateFormat(String pattern, ULocale loc) {
        super(pattern, loc);
        setDateFormatSymbols(new PersianDateFormatSymbols(loc));
        // Should we check if the locale is for Iran or Afghanistan?
        // Anyway, I don't think this class should be used for any other calendar type.
        setCalendar(new PersianCalendar(loc));
    }
}
