package com.example.startdemo.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class JodaTimeUtils {
    public JodaTimeUtils() {
    }

    public static String dateToStr(Date date, JodaTimeUtils.DateFormat fmt) {
        if (date == null) {
            return "";
        } else {
            DateTime dateTime = new DateTime(date);
            return dateTime.toString(fmt.getFmt());
        }
    }

    public static Date strToDate(String dateTimeStr, JodaTimeUtils.DateFormat fmt) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(fmt.getFmt());
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static long toTimeStamp(String date, JodaTimeUtils.DateFormat fmt) {
        DateTimeFormatter formatter = getFormatter(fmt);
        DateTime dateTime = formatter.parseDateTime(date);
        return dateTime.getMillis() / 1000L;
    }

    public static long toTimeStamp(String date, JodaTimeUtils.DateFormat sourceFmt, JodaTimeUtils.DateFormat targetFmt) {
        if (StringUtils.isBlank(date)) {
            return 0L;
        } else {
            String parse = parse(date, sourceFmt, targetFmt);
            return toTimeStamp(parse, targetFmt);
        }
    }

    public static int toIntTimeStamp(String date, JodaTimeUtils.DateFormat fmt) {
        DateTimeFormatter formatter = getFormatter(fmt);
        DateTime dateTime = formatter.parseDateTime(date);
        return (int) (dateTime.getMillis() / 1000L);
    }

    public static String timestampToString(Integer timestamp, JodaTimeUtils.DateFormat fmt) {
        DateTimeFormatter formatter = getFormatter(fmt);
        DateTime dateTime = new DateTime(timestamp.longValue() * 1000L);
        return dateTime.toString(formatter);
    }

    public static String timestampToString(Long timestamp, JodaTimeUtils.DateFormat fmt) {
        DateTimeFormatter formatter = getFormatter(fmt);
        DateTime dateTime = new DateTime(timestamp * 1000L);
        return dateTime.toString(formatter);
    }

    public static boolean formatCheck(String date, JodaTimeUtils.DateFormat fmt) {
        DateTimeFormatter formatter = getFormatter(fmt);

        try {
            formatter.parseDateTime(date);
            return true;
        } catch (Exception var4) {
            return false;
        }
    }

    public static String parse(String date, JodaTimeUtils.DateFormat sourceFmt, JodaTimeUtils.DateFormat targetFmt) {
        DateTimeFormatter source = getFormatter(sourceFmt);
        DateTime dateTime = source.parseDateTime(date);
        DateTimeFormatter target = getFormatter(targetFmt);
        return dateTime.toString(target);
    }

    public static String plusDays(String date, int days, JodaTimeUtils.DateFormat fmt) {
        DateTimeFormatter formatter = getFormatter(fmt);
        DateTime dateTime = formatter.parseDateTime(date);
        DateTime plusDays = dateTime.plusDays(days);
        return plusDays.toString(formatter);
    }

    public static String yesterday(String date, JodaTimeUtils.DateFormat fmt) throws Exception {
        if (StringUtils.isEmpty(date)) {
            throw new Exception("日期不能为空");
        } else if (fmt != null) {
            throw new Exception("格式化参数不能为空");
        } else {
            DateTimeFormatter formatter = getFormatter(fmt);
            DateTime dateTime = formatter.parseDateTime(date);
            DateTime yesterday = dateTime.plusDays(-1);
            return yesterday.toString(formatter);
        }
    }

    public static String yesterday() throws Exception {
        String date = DateTime.now().toString(getFormatter(JodaTimeUtils.DateFormat.DATE_FORMAT));
        return yesterday(date);
    }

    public static String yesterday(String date) throws Exception {
        return yesterday(date, JodaTimeUtils.DateFormat.DATE_FORMAT);
    }

    public static String yesterday(JodaTimeUtils.DateFormat fmt) throws Exception {
        String date = DateTime.now().toString(getFormatter(fmt));
        return yesterday(date, fmt);
    }

    public static String lastWeekFirstDay(String date, JodaTimeUtils.DateFormat fmt) throws Exception {
        if (StringUtils.isEmpty(date)) {
            throw new Exception("日期不能为空");
        } else if (fmt != null) {
            throw new Exception("格式化参数不能为空");
        } else {
            DateTimeFormatter formatter = getFormatter(fmt);
            DateTime dateTime = formatter.parseDateTime(date);
            DateTime lastWeek = dateTime.plusWeeks(-1);
            int dayOfWeek = lastWeek.getDayOfWeek();
            DateTime lastWeekFirstDay = lastWeek.plusDays(1 - dayOfWeek);
            return lastWeekFirstDay.toString(formatter);
        }
    }

    public static String lastWeekFirstDay(String date) throws Exception {
        return lastWeekFirstDay(date, JodaTimeUtils.DateFormat.DATE_FORMAT);
    }

    public static String lastWeekFirstDay() throws Exception {
        DateTimeFormatter formatter = getFormatter(JodaTimeUtils.DateFormat.DATE_FORMAT);
        String now = DateTime.now().toString(formatter);
        return lastWeekFirstDay(now);
    }

    public static String lastWeekLastDay(String date, JodaTimeUtils.DateFormat fmt) throws Exception {
        if (StringUtils.isEmpty(date)) {
            throw new Exception("日期不能为空");
        } else if (fmt != null) {
            throw new Exception("格式化参数不能为空");
        } else {
            DateTimeFormatter formatter = getFormatter(fmt);
            DateTime dateTime = formatter.parseDateTime(date);
            DateTime lastWeek = dateTime.plusWeeks(-1);
            int dayOfWeek = lastWeek.getDayOfWeek();
            DateTime lastWeekLastDay = lastWeek.plusDays(7 - dayOfWeek);
            return lastWeekLastDay.toString(formatter);
        }
    }

    public static String lastWeekLastDay(String date) throws Exception {
        return lastWeekFirstDay(date, JodaTimeUtils.DateFormat.DATE_FORMAT);
    }

    public static String lastWeekLastDay() throws Exception {
        DateTimeFormatter formatter = getFormatter(JodaTimeUtils.DateFormat.DATE_FORMAT);
        String now = DateTime.now().toString(formatter);
        return lastWeekLastDay(now);
    }

    private static DateTimeFormatter getFormatter(JodaTimeUtils.DateFormat fmt) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(fmt.getFmt());
        return dateTimeFormatter;
    }

    public static void main(String[] args) {
        Date date = strToDate("2018-7-1", JodaTimeUtils.DateFormat.DATE_FORMAT);
        System.out.println(date.toString());
    }

    public static Integer intTimestamp() {
        long l = System.currentTimeMillis() / 1000L;
        return (int) l;
    }

    public static Long longTimestamp() {
        return System.currentTimeMillis() / 1000L;
    }

    public static enum DateFormat {
        YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
        YYYY_MM_FORMAT("yyyy-MM"),
        DATE_FORMAT("yyyy-MM-dd"),
        DATETIME_FORMAT("yyyy-MM-dd HH:mm:ss"),
        YYYY_MM_DD_HH_MM_FORMAT("yyyy-MM-dd HH:mm");

        private String fmt;

        private DateFormat(String fmt) {
            this.fmt = fmt;
        }

        public String getFmt() {
            return this.fmt;
        }
    }
}