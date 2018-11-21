package com.vladislavliudchyk.fitnessapp.loseit.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class that allows to work with Date format
 */
public class PickDate {

    /**
     * This method convert milliseconds to String according to the date pattern
     * @param millisecond Value of the time unit
     * @return Value of the string contains result date
     */
    public static String millisecondTimeToString(long millisecond){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date resultDate = new Date(millisecond);
        return sdf.format(resultDate);
    }

    /**
     * This method converts from Date to String
     * @param date Value of the date in Date format
     * @return Value of the date in String format
     */
    public static String dateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * This method converts from String to Date format
     * @param dateString Value of the date in String format
     * @return Value of the date in Date format
     */
    public static Date stringToDate(String dateString){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
