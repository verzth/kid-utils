package id.kiosku.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Dodi on 11/11/2016.
 */

public class IndonesianDate {
    public static String get(String DATE,String format){
        try {
            if(!DATE.equals("0000-00-00")) {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(DATE);
                return new SimpleDateFormat(format, new Locale("in", "ID")).format(date);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    public static String get(){
        try {
            return new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("in", "ID")).format(new Date());
        }catch (Exception e){
            return null;
        }
    }
    public static String getDate(){
        try {
            return new SimpleDateFormat("dd MMMM yyyy", new Locale("in", "ID")).format(new Date());
        }catch (Exception e){
            return null;
        }
    }
    public static String getDayName(){
        try {
            return new SimpleDateFormat("EEEE", new Locale("in", "ID")).format(new Date());
        }catch (Exception e){
            return null;
        }
    }
    public static String getDay(){
        try {
            return new SimpleDateFormat("dd", new Locale("in", "ID")).format(new Date());
        }catch (Exception e){
            return null;
        }
    }
    public static String getMonth(){
        try {
            return new SimpleDateFormat("MMMM", new Locale("in", "ID")).format(new Date());
        }catch (Exception e){
            return null;
        }
    }
    public static String getYear(){
        try {
            return new SimpleDateFormat("yyyy", new Locale("in", "ID")).format(new Date());
        }catch (Exception e){
            return null;
        }
    }

    public static String get(String DATE){
        try {
            if(!DATE.equals("0000-00-00")) {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(DATE);
                return new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("in", "ID")).format(date);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
    public static String getDate(String DATE){
        try {
            if(!DATE.equals("0000-00-00")) {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(DATE);
                return new SimpleDateFormat("dd MMMM yyyy", new Locale("in", "ID")).format(date);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
    public static String getDayName(String DATE){
        try {
            if(!DATE.equals("0000-00-00 00:00:00")) {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(DATE);
                return new SimpleDateFormat("EEEE", new Locale("in", "ID")).format(date);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
    public static String getDay(String DATE){
        try {
            if(!DATE.equals("0000-00-00")) {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(DATE);
                return new SimpleDateFormat("dd", new Locale("in", "ID")).format(date);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
    public static String getMonth(String DATE){
        try {
            if(!DATE.equals("0000-00-00")) {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(DATE);
                return new SimpleDateFormat("MMMM", new Locale("in", "ID")).format(date);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
    public static String getYear(String DATE){
        try {
            if(!DATE.equals("0000-00-00")) {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(DATE);
                return new SimpleDateFormat("yyyy", new Locale("in", "ID")).format(date);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
}
