package com.yyq.iterror.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 日期时间处理工具
 * @author yu.li
 */
public class DateUtil {
    public static synchronized String getCurrentDay() {
        //获取当前的系统时间。
        Date date = new Date();
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    public static synchronized String getDay(Date date) {
        //获取当前的系统时间。
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static synchronized Date getDay(String dateStr) {
        //获取当前的系统时间。
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            return date;
        } catch (ParseException e) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse("1997-07-01");
            } catch (ParseException e1) {
                return null;
            }
        }
    }

    public static synchronized Date getDayStart(Date dateTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(dateTime);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 返回 20110605 类似的整数
     * @param date
     * @return
     */
    public static int getDayInt(Date date) {
        if (date == null) {
            date = new Date();
        }
        String s = new SimpleDateFormat("yyyyMMdd").format(date);
        return Integer.parseInt(s);
    }

    /**
     * 取当前周的周一时间
     * @return
     */
    public static Date getFistWeekDay() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 2;
        if (dayOfWeek == -1) {
            dayOfWeek = 6;
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, -1 * dayOfWeek);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        Date sundayDay = currentDate.getTime();
        return sundayDay;
    }

    public static Date getNextDay(Integer signTime) {
        try {
            String dateStr = "" + signTime;
            Date date = new SimpleDateFormat("yyyyMMdd").parse(dateStr);
            return addDate(date, 1);
        } catch (ParseException e) {
            return null;
        }
    }

    public static synchronized String getTodayStr() {
        //获取当前的系统时间。
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static synchronized String getCurrentDayHours() {
        //获取当前的系统时间。
        Date date = new Date();
        return new SimpleDateFormat("yyyyMMddHH").format(date);
    }

    /**
     * 获取当前日期的星期
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }


    /**
     * 获取某个日期所在周的周一日期:  yyyyMMdd
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            c.add(Calendar.DATE, -1);
        }
        Calendar cf = Calendar.getInstance();
        cf.setTime(c.getTime());
        cf.set(Calendar.DAY_OF_WEEK, cf.getFirstDayOfWeek());
        cf.add(Calendar.DATE, 1);
        cf.set(Calendar.HOUR_OF_DAY, 0);
        cf.set(Calendar.MINUTE, 0);
        cf.set(Calendar.SECOND, 0);
        Date d = cf.getTime();
        return d;
    }

    public static synchronized String getCurrentTimeCode() {
        //获取当前的系统时间。
        Date date = new Date();
        String time = new SimpleDateFormat("yyyyMMddHHmm").format(date);
        String mm = time.substring(time.length() - 2, time.length());
        String pre = time.substring(0, time.length() - 2);
        int m = Integer.parseInt(mm);
        int x = m / 20 + 1;
        return pre + x;
    }

    public static synchronized String getYesterday() {
        //获取当前的系统时间。
        Date date = new Date();
        date = addDate(date, -1);
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    /**
     * 获取当前时间
     * @return 时间戳
     */
    public static int getCurrentTime() {
        Long curent = System.currentTimeMillis() / 1000L;
        return curent.intValue();
    }

    public static synchronized String getDayNoSplit(Date date) {
        //获取当前的系统时间。年
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    /**
     * 取增加天数后的日期时间
     * @param dateTime 时间
     * @return 增加天数后的日期时间
     */
    public static synchronized Date addDateStart(Date dateTime, int offset) {
        Calendar c = Calendar.getInstance();
        c.setTime(dateTime);
        c.add(Calendar.DAY_OF_MONTH, offset);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 取增加天数后的日期时间
     * @param dateTime 时间
     * @return 增加天数后的日期时间
     */
    public static synchronized Date addDateEnd(Date dateTime, int offset) {
        Calendar c = Calendar.getInstance();
        c.setTime(dateTime);
        c.add(Calendar.DAY_OF_MONTH, offset);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 取增加天数后的日期时间
     * @param dateTime 时间
     * @return 增加天数后的日期时间
     */
    public static synchronized Date addDate(Date dateTime, int offset) {
        Calendar c = Calendar.getInstance();
        c.setTime(dateTime);
        c.add(Calendar.DAY_OF_MONTH, offset);
        return c.getTime();
    }

    /**
     * 取增加天数后的日期时间
     * @param dateTime 时间
     * @return 增加天数后的日期时间
     */
    public static synchronized Date addYear(Date dateTime, int offset) {
        Calendar c = Calendar.getInstance();
        c.setTime(dateTime);
        c.add(Calendar.YEAR, offset);
        return c.getTime();
    }

    /**
     * 取日期时间差
     * @param startTime 时间
     * @param endTime   时间
     * @return 取日期时间差（分钟）
     */
    public static synchronized Long getTimeDiff(Date startTime, Date endTime) {
        Instant instant = startTime.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime startTimeLocal = instant.atZone(zoneId).toLocalDateTime();
        Instant instantEnd = endTime.toInstant();
        ZoneId zoneIdEnd = ZoneId.systemDefault();
        LocalDateTime endTimeLocal = instantEnd.atZone(zoneIdEnd).toLocalDateTime();
        return Duration.between(startTimeLocal, endTimeLocal).toMinutes();
    }

    public static void main(String[] args) {

        System.out.println(getDayStart(new Date()));

    }

    public static synchronized String getLocalDateStr(LocalDateTime editTime) {
        String editTimeStr = editTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return editTimeStr;
    }

    public static synchronized String getLocalDate(LocalDateTime editTime) {
        String editTimeStr = editTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return editTimeStr;
    }

    public static synchronized LocalDateTime getDateToLocal(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime startTimeLocal = instant.atZone(zoneId).toLocalDateTime();
        return startTimeLocal;
    }

    public static synchronized LocalDateTime getLocalDateFromStr(String localDate) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(localDate, df);
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(date, localTime);
        return localDateTime;
    }


    public static Date localDate2Date(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }


    public static Date localDate2Date(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    public static int getBetweenDay(Date begin_date, Date end_date) {
        int day;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            if (begin_date == null || end_date == null) {
                return 0;
            }
            String begin = sdf.format(begin_date);
            begin_date = sdf.parse(begin);
            String end = sdf.format(end_date);
            end_date = sdf.parse(end);
            day = (int) ((end_date.getTime() - begin_date.getTime()) / (24 * 60 * 60 * 1000));
        } catch (Exception e) {
            return 0;
        }
        return day;
    }

    /**
     * 取指定时间与当天开始时间的差
     * @param endTime 时间
     * @return 取日期时间差（分钟）
     */
    public static synchronized Long getToEndTimeDiff(Date endTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(endTime);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        Date startTime = c.getTime();
        return getTimeDiff(startTime, endTime);
    }

    /**
     * 取指定时间与当天结束时间的差
     * @param startTime 时间
     * @return 取日期时间差（分钟）
     */
    public static synchronized Long getToStartTimeDiff(Date startTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        Date endTime = c.getTime();
        return getTimeDiff(startTime, endTime);
    }


    /**
     * 是否跨天
     * @param startTime 时间
     * @param endTime   时间
     * @return 是跨天
     */
    public static boolean isCrossDay(Date startTime, Date endTime) {
        Instant instant = startTime.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime startTimeLocal = instant.atZone(zoneId).toLocalDateTime();
        Instant instantEnd = endTime.toInstant();
        ZoneId zoneIdEnd = ZoneId.systemDefault();
        LocalDateTime endTimeLocal = instantEnd.atZone(zoneIdEnd).toLocalDateTime();
        return endTimeLocal.getDayOfMonth() > startTimeLocal.getDayOfMonth();
    }

    /**
     * 是否超时
     * @param startTime 开始时间戳
     * @param delay     延时（秒）
     * @return true超时
     */
    public static boolean isExpire(long startTime, long delay) {
        long _startTime = delay + startTime;
        return (System.currentTimeMillis() / 1000L) > _startTime / 1000L;
    }

    public static Date localDateTimeConvertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
    }

    /**
     * 是否超时
     * @param startTime 开始时间
     * @param unit      时间单位
     * @param count     时间限制
     * @return
     */
    public static boolean isExpiredTotalTask(LocalDateTime startTime, String unit, int count) {
        Duration duration = Duration.between(startTime, LocalDateTime.now());
        switch (unit) {
            case "天":
                long days = duration.toDays();
                return days >= count;
            case "分钟":
                long minutes = duration.toMinutes();
                return minutes >= count;
            default:
                return false;
        }
    }


    public static boolean isExpiredTotalTask(String unit, int count) {
        if ("次".equalsIgnoreCase(unit)) {
            long num = count;
            if (num == 0) {
                return false;
            }
            return num >= count;
        }
        return false;
    }


    /**
     * 转分钟
     * @param currentTimeLong
     * @return
     */
    public static int getMinutes(long currentTimeLong) {
        return (int) (currentTimeLong / (1000 * 60L));
    }

    /**
     * 转秒
     * @param currentTimeLong
     * @return
     */
    public static int getSecond(long currentTimeLong) {
        return (int) (currentTimeLong / (1000L));
    }

    /**
     * 获取当前的系统时间
     */
    public static String getNowStr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 获取当前的系统时间
     */
    public static String getDateStr(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
