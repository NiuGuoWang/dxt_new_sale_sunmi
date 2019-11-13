package com.dxt.retail4sunmi.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author star
 * @date 2017/11/14 0014
 */

public class DateUtils {

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static String getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return formatter.format(currentTime);
    }

    public static String getDate(String str) {
        try {
            long time = Long.parseLong(str);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            return formatter.format(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null";
    }

    // 本周一
    public static String getThisMonDayString() {
        Calendar cal = Calendar.getInstance();
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        return getDateString(cal);
    }

//    public static String getThisSunDayString() {
//        Calendar cal = Calendar.getInstance();
//        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
//        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
//        if (1 == dayWeek) {
//            cal.add(Calendar.DAY_OF_MONTH, -1);
//            return getDateString(cal);
//        }
//        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
//        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
//        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
//        return getDateString(cal);
//    }

    public static String getLastMonDayString() {
        Calendar cal = Calendar.getInstance();
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day - 7);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        return getDateString(cal);
    }

    public static String getLastSunDayString() {
        Calendar cal = Calendar.getInstance();
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
            return getDateString(cal);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day - 1);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        return getDateString(cal);
    }

    public static String getYesterdayString() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return getDateString(cal);
    }


    public static String getLastMonthFristDay() {
        Calendar cal = Calendar.getInstance();//获取当前日期
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, cal
                .getActualMinimum(Calendar.DAY_OF_MONTH));
        return getDateString(cal);
    }

    public static String getLastMonthLastDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, cal
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        return getDateString(cal);
    }

    public static String getThisMonthFristDay() {
        Calendar cal = Calendar.getInstance();//获取当前日期
        cal.set(Calendar.DAY_OF_MONTH, cal
                .getActualMinimum(Calendar.DAY_OF_MONTH));
        return getDateString(cal);
    }

//    public static String getThisMonthLastDay() {
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.DAY_OF_MONTH, cal
//                .getActualMaximum(Calendar.DAY_OF_MONTH));
//        return getDateString(cal);
//    }

    private static String getDateString(Calendar cal) {
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = format(cal.get(Calendar.MONTH) + 1);
        String day = format(cal.get(Calendar.DATE));
        return year + month + day;
    }

    private static String format(int num) {
        if (num < 10) {
            return "0" + num;
        } else {
            return String.valueOf(num);
        }
    }
    //是否是当天
    public static boolean isThisDay(long time) {
        Date date = new Date(time);
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        return param.equals(now);
    }
}
