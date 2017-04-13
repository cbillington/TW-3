package com.travelexperts.travelpackages.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by 468364 on 4/11/2017.
 */

public class DateUtils {

    public static String fromMilliToFormatted(Long milli){
        SimpleDateFormat formatter = new SimpleDateFormat("MMM, FF yyyy");

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milli);
        return formatter.format(calendar.getTime());
    }
}
