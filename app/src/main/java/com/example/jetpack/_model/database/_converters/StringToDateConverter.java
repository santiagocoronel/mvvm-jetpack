package com.example.jetpack._model.database._converters;

import android.arch.persistence.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter {

    private static String formatter = "yyyy-MM-dd HH:mm:ssZ";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);

    @TypeConverter
    public static Date toDate(String timestamp) {
        try {
            return timestamp == null ? null : simpleDateFormat.parse(formatter);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @TypeConverter
    public static String toTimestamp(Date date) {
        return date == null ? null : simpleDateFormat.format(date);
    }

}
