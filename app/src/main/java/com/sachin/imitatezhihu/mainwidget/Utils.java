package com.sachin.imitatezhihu.mainwidget;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lenovo on 2016/4/30.
 */
public class Utils {
    private static final String TAG="Utils";

    public static Date getDate(String dateString){
        Date date = null;
        DateFormat format=getDateFormat();
        try {
         date   =format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e(TAG,"date format is not match");
        }
        return date;
    }


    public static DateFormat getDateFormat(){

        SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
        format.setLenient(false);
        return format;
    }

    public static  String test(String sdate){
        Date date=getDate(sdate);
        DateFormat format=getDateFormat();
        String s=format.format(date);//将日期转换为指定format格式的字符串
        return s;

    }

    /**
     * @param dateString
     * @return
     */
  public   static boolean validateDate(String dateString){
        SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
        format.setLenient(false);
        Date date=null;
        try {
            date=format.parse(dateString);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    public static long howFraINDays(Date date){
        Calendar cal= Calendar.getInstance();
        Date today=cal.getTime();
        long today_ms=today.getTime();
        long target_ms=date.getTime();
        return (target_ms-today_ms)/(1000*60*60*24);

    }

}
