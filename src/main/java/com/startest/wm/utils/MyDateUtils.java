package com.startest.wm.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @ProjectName:startestwm
 * @PackageName:com.startest.wm.utils
 * @ClassName:DateUtils
 * @Description: 时间处理工具
 * @author: skj
 * @date 2020/7/29  15:12
 */
public class MyDateUtils {
    /**
     * 日期格式:年月日
     */
    public static final String DATE_PATTERN_SHORT  = "yyyyMMdd";
    /**
     * 日期格式:年-月-日 时:分:秒
     */
    public static final String DATE_PATTERN_SECOND = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式：年/月/日
     */
    public static final String DATE_PATTERN_SIMPLE = "yyyy/MM/dd";
    /**
     * 日期格式:年-月-日
     */
    public static final String DATE_PATTERN_FLAT  = "yyyy-MM-dd";

    /**
     * 年
     */
    public static final String DATE_YEAR = "yyyy";


    /**
     * 获取某季度的开始日期
     * 季度一年四季， 第一季度：2月-4月， 第二季度：5月-7月， 第三季度：8月-10月， 第四季度：11月-1月
     *
     * @param offset 0本季度，1下个季度，-1上个季度，依次类推
     * @return
     */
    public static LocalDate quarterStart(int offset) {
        final LocalDate date = LocalDate.now().plusMonths(offset * 3);
        int month = date.getMonth().getValue();//当月
        int start = 0;
        if (month >= 2 && month <= 4) {//第一季度
            start = 2;
        } else if (month >= 5 && month <= 7) {//第二季度
            start = 5;
        } else if (month >= 8 && month <= 10) {//第三季度
            start = 8;
        } else if ((month >= 11 && month <= 12)) {//第四季度
            start = 11;
        } else if (month == 1) {//第四季度
            start = 11;
            month = 13;
        }
        return date.plusMonths(start - month).with(TemporalAdjusters.firstDayOfMonth());
    }



    public static String quarterStart(String year,String quarter) {
        if (quarter==null||"".equals(quarter)||year==null||"".equals(year)){
            return  "";
        }
        switch (quarter){
            case "0":
                return year + "-01-01";
            case "1":
                return year + "-01-01";
            case "2":
                return year + "-04-01";
            case "3":
                return year + "-07-01";
            case "4":
                return year + "-10-01";
        }
        return  "";
    }
    public static String quarterEnd(String year,String quarter) {
        if (quarter==null||"".equals(quarter)||year==null||"".equals(year)){
            return  "";
        }
        switch (quarter){
            case "0":
                return year + "-12-31";
            case "1":
                return year + "-03-31";
            case "2":
                return year + "-06-30";
            case "3":
                return year + "-09-30";
            case "4":
                return year + "-12-31";
        }
        return  "";
    }

    public static final String[] patterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd","yyyy/MM/dd HH:mm:ss"};
    //判断字符串是否为日期格式
    public static boolean isDate(String param){
        if (param==null) {
            return false;
        }

        try{
            DateUtils.parseDate(param,patterns);
        }catch (ParseException e){
            return false;
        }

        return true;
    }


    /**判断日期字符串能否正确转换格式
     * @param date 日期
     * @return 转换后的日期
     */
    public static Date toDate(String date ){
        DateFormat format = new SimpleDateFormat(DATE_PATTERN_SECOND);

        if(date==null) {
            return null;
        }

        try{
            return format.parse(date);

        }catch (ParseException e){
            return null;
        }
    }


    /**获取当前日期
     * 　格式为yyyyMMdd
     * @return 　日期
     */
    public static String getCurrentDate(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_SHORT);
        return sdf.format(d);
    }

    /**根据指定格式，返回时间
     * @param timeMode 　时间格式　yyyy-MM-dd,yyyy/MM/dd,yyyy-MM-dd HH:mm:ss
     * @return  返回时间：  　
     */
    public static String getCurrentDate(String timeMode){
        if (StringUtils.isBlank(timeMode.trim())) {
            return null;
        }
        Date d = new Date();
        if (DATE_PATTERN_FLAT.equalsIgnoreCase(timeMode)) {
         return new SimpleDateFormat(DATE_PATTERN_FLAT).format(d);
        }
        if (DATE_PATTERN_SECOND.equalsIgnoreCase(timeMode)) {
         return new SimpleDateFormat(DATE_PATTERN_SECOND).format(d);
        }
        if (DATE_PATTERN_SIMPLE.equalsIgnoreCase(timeMode)) {
         return new SimpleDateFormat(DATE_PATTERN_SIMPLE).format(d);
        }
        if (DATE_YEAR.equalsIgnoreCase(timeMode)) {
            return new SimpleDateFormat(DATE_YEAR).format(d);
        }

        return null;
    }



    public static void main(String[] args) {
    }
}
