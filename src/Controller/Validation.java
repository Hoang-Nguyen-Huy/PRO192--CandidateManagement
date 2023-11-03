/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dell Latitude 7490
 */
public class Validation {
    
    public boolean checkName (String inp) {
        if (inp.isEmpty()) {
            System.out.println("This can not be empty!!!");
            return false;
        }
        if (inp.matches("^.*[0-9!@#$%^&*(){}_+-=*/.<>?|].*")) {
            System.out.println("This can only contain characters");
            return false;
        }
        return true;
    }
    
    public boolean checkPhone(String inp) {
        if (inp.isEmpty()) {
            System.out.println("Phone can not be empty!!!");
            return false;
        }
        
        if (!inp.matches("^[0-9]+$")) {
            System.out.println("Phone numbers can only contain digits");
            return false;
        }

        if (!inp.matches("^0[0-9]{9}$")) {
            System.out.println("Invalid phone number format. It should start with 0 and followed by 9 digits");
            return false;
        }
        
        if (inp.length() != 10) {
            System.out.println("Phone must be have 10 characters");
            return false;
        }

        if (!FileHandler.duplicate(inp, Management.fileName)) {
            System.out.println("Duplicated phone number !!!");
            return false;
        }
        return true;
    }
    
    public boolean checkEmail(String inp) {
        if (inp.isEmpty()) {
            System.out.println("Email can not be empty!!!");
            return false;
        }
        
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if(!inp.matches(emailRegex)) {
            System.out.println("Email is not valid!!!");
            return false;
        }

        if (!FileHandler.duplicate(inp, Management.fileName)) {
            System.out.println("Duplicated email!!!");
            return false;
        }
        return true;
    }
    
    public boolean checkDate (String dateString) {
        Pattern dateRegexPattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
           
        Matcher dateMatcher = dateRegexPattern.matcher(dateString);

        if (dateMatcher.matches()) {

           dateMatcher.reset();

           if (dateMatcher.find()) {
               String day = dateMatcher.group(1);
               String month = dateMatcher.group(2);
               int year = Integer.parseInt(dateMatcher.group(3));

               if ("31".equals(day) && 
                  ("4".equals(month) || "6".equals(month) || "9".equals(month) ||
                   "11".equals(month) || "04".equals(month) || "06".equals(month) || 
                   "09".equals(month))) {
                   return false; // 1, 3, 5, 7, 8, 10, 12 has 31 days
               } else if ("2".equals(month) || "02".equals(month)) {
                    //leap year
                    if (year % 4 == 0) {
                        return !"30".equals(day) && !"31".equals(day);
                    } else {
                        return !"29".equals(day) && !"30".equals(day) && !"31".equals(day);
                    }
               } else {
                   return true;
               }
           } else {
               return false;
           }
        } else {
            return false;
        }
    }

    public boolean checkType (String inp) {
        try {
            int x = Integer.parseInt(inp);
            if (x == 1 || x == 2 || x == 3) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    public boolean checkInt (String inp) {
        try {
            Integer.parseInt(inp);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checkRank(String inp) {
        String [] data = new String[]{"Excellent", "Good", "Bad"};
        for (String s : data) {
            if (s.equals(inp)) {
                return true;
            }
        }
        return false;
    }

}
