package io.kreatimont.task.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static DateFormatter instance = new DateFormatter();

    private DateFormatter() {

    }

    public String convertDateToString(Date date) {
        return dateFormat.format(date);
    }

    public Date convertStringToDate(String dateString) {
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

}
