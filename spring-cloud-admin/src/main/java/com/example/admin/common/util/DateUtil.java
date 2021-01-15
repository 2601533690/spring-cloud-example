package com.example.admin.common.util;

import org.springframework.util.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    public static Date toDate(Long dateInLong) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateInLong);
        return cal.getTime();
    }

    public static Calendar toCal(Long dateInLong) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateInLong);
        return cal;
    }

    public static Calendar toCal(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static Date toDate(int year, int month, int date, int hour,
                              int minute, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, date, hour, minute, sec);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date futureDate(int milli) {
        Date date = new Date();
        date.setTime(System.currentTimeMillis() + milli);
        return date;
    }

    public static Date toDate(int year, int month, int date, int hour,
                              int minute, int sec, int milli) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, date, hour, minute, sec);
        cal.set(Calendar.MILLISECOND, milli);
        return cal.getTime();
    }

    public static Date currentTime() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    public static Date currentTime(String zone) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(zone));
        return cal.getTime();
    }

    public static long currentTimeInLong() {
        return System.currentTimeMillis();
    }

    public static String currentTimeInString() {
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
        return format.format(Calendar.getInstance().getTime());
    }

    public static String currentTimeInString(String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(Calendar.getInstance().getTime());
    }

    public static String timeToString(long time, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(time);
    }

    public static boolean checkValidDate(String dateStr, String format) {
        if (!StringUtils.hasText(dateStr)) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);

        try {
            @SuppressWarnings("unused")
            Date date = sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public static long stringToTime(String dateStr) {
        if (!checkValidDate(dateStr, "yyyy-MM-dd")) {
            dateStr = DateUtil.currentTimeInString("yyyy-MM-dd");
        }
        String[] dateArr = dateStr.split("-");
        Date date = DateUtil.toDate(Integer.parseInt(dateArr[0]),
                Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[2]), 0,
                0, 0);

        Calendar cal = DateUtil.toCal(date);
        return cal.getTimeInMillis();

    }

    public static long stringToTime(String dateStr, String pattern) throws ParseException {
        if (!checkValidDate(dateStr, pattern)) {
            dateStr = DateUtil.currentTimeInString("yyyy-MM-dd hh:mm:ss");
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar cal = DateUtil.toCal(format.parse(dateStr));
        return cal.getTimeInMillis();

    }

    public static String convertDateExpression(long time, String pattern) {
        Calendar cal = Calendar.getInstance();
        int todayYear = cal.get(Calendar.YEAR);
        int todayMonth = cal.get(Calendar.MONTH);
        int todayDay = cal.get(Calendar.DAY_OF_MONTH);

        cal.add(Calendar.DAY_OF_MONTH, -1);
        int yesterdayYear = cal.get(Calendar.YEAR);
        int yesterdayMonth = cal.get(Calendar.MONTH);
        int yesterdayDay = cal.get(Calendar.DAY_OF_MONTH);

        cal.add(Calendar.DAY_OF_MONTH, 2);
        int tomorrowYear = cal.get(Calendar.YEAR);
        int tomorrowMonth = cal.get(Calendar.MONTH);
        int tomorrowDay = cal.get(Calendar.DAY_OF_MONTH);

        Calendar calPassedIn = DateUtil.toCal(time);
        if (todayYear == calPassedIn.get(Calendar.YEAR)
                && todayMonth == calPassedIn.get(Calendar.MONTH)
                && todayDay == calPassedIn.get(Calendar.DAY_OF_MONTH)) {
            return "今天";
        }

        if (yesterdayYear == calPassedIn.get(Calendar.YEAR)
                && yesterdayMonth == calPassedIn.get(Calendar.MONTH)
                && yesterdayDay == calPassedIn.get(Calendar.DAY_OF_MONTH)) {
            return "昨天";
        }

        if (tomorrowYear == calPassedIn.get(Calendar.YEAR)
                && tomorrowMonth == calPassedIn.get(Calendar.MONTH)
                && tomorrowDay == calPassedIn.get(Calendar.DAY_OF_MONTH)) {
            return "明天";
        }

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(calPassedIn.getTime());
    }

}
