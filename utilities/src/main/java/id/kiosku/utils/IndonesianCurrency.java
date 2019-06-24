package id.kiosku.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by AMP on 16-Oct-15.
 */
public class IndonesianCurrency {
    public static String get(Integer plain){
        if(plain!=null) {
            DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();

            symbols.setCurrencySymbol("Rp ");
            symbols.setMonetaryDecimalSeparator(',');
            symbols.setGroupingSeparator('.');

            format.setDecimalFormatSymbols(symbols);

            return format.format(plain);
        }else{
            return null;
        }
    }

    public static String getWithFree(Integer plain) {
        if(plain!=null) {
            if (plain > 0) {
                DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();

                symbols.setCurrencySymbol("Rp ");
                symbols.setMonetaryDecimalSeparator(',');
                symbols.setGroupingSeparator('.');

                format.setDecimalFormatSymbols(symbols);

                return format.format(plain);
            } else {
                return "Free";
            }
        }else{
            return null;
        }
    }
    public static String get(Long plain){
        if(plain!=null) {
            DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();

            symbols.setCurrencySymbol("Rp ");
            symbols.setMonetaryDecimalSeparator(',');
            symbols.setGroupingSeparator('.');

            format.setDecimalFormatSymbols(symbols);

            return format.format(plain);
        }else{
            return null;
        }
    }

    public static String getWithFree(Long plain) {
        if(plain!=null) {
            if (plain > 0) {
                DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();

                symbols.setCurrencySymbol("Rp ");
                symbols.setMonetaryDecimalSeparator(',');
                symbols.setGroupingSeparator('.');

                format.setDecimalFormatSymbols(symbols);

                return format.format(plain);
            } else {
                return "Free";
            }
        }else{
            return null;
        }
    }

    public static String get(Short plain){
        if(plain!=null) {
            DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();

            symbols.setCurrencySymbol("Rp ");
            symbols.setMonetaryDecimalSeparator(',');
            symbols.setGroupingSeparator('.');

            format.setDecimalFormatSymbols(symbols);

            return format.format(plain);
        }else{
            return null;
        }
    }

    public static String getWithFree(Short plain) {
        if(plain!=null) {
            if (plain > 0) {
                DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();

                symbols.setCurrencySymbol("Rp ");
                symbols.setMonetaryDecimalSeparator(',');
                symbols.setGroupingSeparator('.');

                format.setDecimalFormatSymbols(symbols);

                return format.format(plain);
            } else {
                return "Free";
            }
        }else{
            return null;
        }
    }

    public static String get(Float plain){
        if(plain!=null) {
            DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();

            symbols.setCurrencySymbol("Rp ");
            symbols.setMonetaryDecimalSeparator(',');
            symbols.setGroupingSeparator('.');

            format.setDecimalFormatSymbols(symbols);

            return format.format(plain);
        }else{
            return null;
        }
    }

    public static String getWithFree(Float plain) {
        if(plain!=null) {
            if (plain > 0) {
                DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();

                symbols.setCurrencySymbol("Rp ");
                symbols.setMonetaryDecimalSeparator(',');
                symbols.setGroupingSeparator('.');

                format.setDecimalFormatSymbols(symbols);

                return format.format(plain);
            } else {
                return "Free";
            }
        }else{
            return null;
        }
    }

    public static String get(Double plain){
        if(plain!=null) {
            DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();

            symbols.setCurrencySymbol("Rp ");
            symbols.setMonetaryDecimalSeparator(',');
            symbols.setGroupingSeparator('.');

            format.setDecimalFormatSymbols(symbols);

            return format.format(plain);
        }else{
            return null;
        }
    }

    public static String getWithFree(Double plain) {
        if(plain!=null) {
            if (plain > 0) {
                DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();

                symbols.setCurrencySymbol("Rp ");
                symbols.setMonetaryDecimalSeparator(',');
                symbols.setGroupingSeparator('.');

                format.setDecimalFormatSymbols(symbols);

                return format.format(plain);
            } else {
                return "Free";
            }
        }else{
            return null;
        }
    }

    public static String get(String plain){
        if(plain!=null) {
            try{
                return get(Long.valueOf(plain));
            }catch (Exception e){
                return null;
            }
        }else{
            return null;
        }
    }

    public static String getWithFree(String plain) {
        if(plain!=null) {
            try{
                return getWithFree(Long.valueOf(plain));
            }catch (Exception e){
                return null;
            }
        }else{
            return null;
        }
    }
}
