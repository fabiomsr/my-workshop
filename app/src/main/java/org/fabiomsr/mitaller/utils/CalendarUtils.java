package org.fabiomsr.mitaller.utils;

import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {

  private static final String FULL_MONTH_PATTERN = "MMMM";
  private static final String SHORT_MONTH_PATTERN = "MMM";
  private static final String FULL_DATE_PATTERN = "EEEE, MMMM d, yyyy";

  public static int getDay(Calendar calendar, Date date){
    calendar.setTime(date);
    return calendar.get(Calendar.DAY_OF_MONTH);
  }

  public static String getAbbreviateMonthName(Date date){
    return getFormatDate(date, SHORT_MONTH_PATTERN);
  }

  public static String getMonthName(Date date){
    return getFormatDate(date, FULL_MONTH_PATTERN);
  }

  public static String getFullFormatDate(Date date) {
    return getFormatDate(date, FULL_DATE_PATTERN);
  }

  private static String getFormatDate(Date date, String pattern){
    SimpleDateFormat format = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
    format.applyPattern(pattern);
    return format.format(date);
  }


  public static Date createDate(DatePicker datePicker){
    int year = datePicker.getYear();
    int month = datePicker.getMonth();
    int day = datePicker.getDayOfMonth();

    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month, day);
    return calendar.getTime();
  }


}
