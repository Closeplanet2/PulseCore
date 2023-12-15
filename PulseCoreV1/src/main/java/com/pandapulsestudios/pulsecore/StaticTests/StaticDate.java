package com.pandapulsestudios.pulsecore.StaticTests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StaticDate {
    private static final java.text.SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd-HH-mm-ss", Locale.ENGLISH);

    public static String ToString(java.text.SimpleDateFormat format, Object date){ return ToString(format, (Date) date); }
    public static String ToString(SimpleDateFormat format, Date date){
        if(format == null) format = SimpleDateFormat;
        return SimpleDateFormat.format(((Date) date));
    }

    public static Date ToDate(SimpleDateFormat format, Object date) throws ParseException { return ToDate(format, date.toString()); }
    public static Date ToDate(SimpleDateFormat format, String date) throws ParseException {
        if(format == null) format = SimpleDateFormat;
        return SimpleDateFormat.parse(date);
    }
}
