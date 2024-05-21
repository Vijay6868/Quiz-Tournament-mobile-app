package com.example.myapplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Validator {
    // Constants for minimum length requirements
    private static final int MIN_NAME_LENGTH = 4;
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static boolean isValidName(String name) {
        // Implement your validation logic for usernames here
        return isValidString(name, MIN_NAME_LENGTH);
    }

    public static boolean isValidPassword(String password) {

        return isValidString(password, MIN_PASSWORD_LENGTH);
    }

    public static boolean isValidEmail(String email) {

        return email != null && email.matches(EMAIL_REGEX);
    }

    //check password value matches with confirm password value
    public static boolean isValidMatch(String password, String c_password){
        return password.equals(c_password);
    }

    // Generic method to validate any string attribute based on minimum length
    private static boolean isValidString(String attribute, int minLength) {
        return attribute != null && attribute.length() >= minLength;
    }
    public static boolean isValidDate(String date) {
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH);
        int currentDay = c.get(Calendar.DAY_OF_MONTH);

        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date selectedDateObj = sdf.parse(date);

            // Compare selected date with current date
            Date currentDateObj = sdf.parse(currentDay + "/" + (currentMonth + 1) + "/" + currentYear);
            if (selectedDateObj.before(currentDateObj)) {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;


    }
    public static boolean isDateSmallerThenOriginal(String updated,String date) {
        
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date selectedDateObj = sdf.parse(date);

            // Compare selected date with current date
            Date updatedDateObj = sdf.parse(updated);
            if (updatedDateObj.before(selectedDateObj)) {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;


    }
    public static boolean isToday(String dateString) {
        // Define the date format that matches the input string
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Parse the input date string
            Date inputDate = dateFormat.parse(dateString);

            // Get today's date without time part
            Date today = new Date();
            String todayString = dateFormat.format(today);
            Date todayDate = dateFormat.parse(todayString);

            // Compare the dates
            return inputDate.equals(todayDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isDateTodayOrGreater(String date) {
        try {
            Date quizDate = sdf.parse(date);
            Date currentDate = new Date();
            return quizDate.after(currentDate); // Returns true if quizDate is equal to or after currentDate
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isDateTodayOrSmaller(String date) {
        try {
            Date quizDate = sdf.parse(date);
            Date currentDate = new Date();
            return !quizDate.after(currentDate); // Returns true if quizDate is equal to or before currentDate
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isDateGreaterThanToday(String date) {
        try {
            Date quizDate = sdf.parse(date);
            Date currentDate = new Date();
            return quizDate.after(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }


}
