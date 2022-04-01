/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author letha
 */
public class Validator {
    public static boolean isDate(String date) {
        if (!isValidString(date)) return false;
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date tobeValidate = sdf.parse(date);
            if (tobeValidate.compareTo(sdf.parse(sdf.format(new Date()))) < 0) {
                return false;
            }
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    public static boolean isDateFromTo(String from, String to) {
        if (!isValidString(from) || !isValidString(to)) return false;
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date fromValidate = sdf.parse(from);
            Date toValidate = sdf.parse(to);
            if (fromValidate.compareTo(sdf.parse(sdf.format(new Date()))) < 0) {
                return false;
            }
            
            if (toValidate.compareTo(sdf.parse(sdf.format(new Date()))) < 0) {
                return false;
            }
            
            if (fromValidate.compareTo(toValidate) >= 0) {
                return false;
            }
            
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isNumberic(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public static boolean isValidString(String string) {
        if (string != null && !string.equals("")) {
            return true;
        }
        return false;
    }
}
