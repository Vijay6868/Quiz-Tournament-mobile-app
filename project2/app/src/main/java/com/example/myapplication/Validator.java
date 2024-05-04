package com.example.myapplication;

public class Validator {
    // Constants for minimum length requirements
    private static final int MIN_USERNAME_LENGTH = 4;
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MIN_NAME_LENGTH = 2; // Minimum length for first name and last name
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static boolean isValidUsername(String username) {
        // Implement your validation logic for usernames here
        return isValidString(username, MIN_USERNAME_LENGTH);
    }

    public static boolean isValidPassword(String password) {

        return isValidString(password, MIN_PASSWORD_LENGTH);
    }

    public static boolean isValidFirstName(String firstName) {
        // Validation logic for first name: contains only letters and has minimum length MIN_NAME_LENGTH
        return isValidString(firstName, MIN_NAME_LENGTH) && firstName.matches("[a-zA-Z]+");
    }

    public static boolean isValidLastName(String lastName) {
        // Validation logic for last name: contains only letters and has minimum length MIN_NAME_LENGTH
        return isValidString(lastName, MIN_NAME_LENGTH) && lastName.matches("[a-zA-Z]+");
    }


    public static boolean isValidEmail(String email) {

        return email != null && email.matches(EMAIL_REGEX);
    }

    public static boolean isValidBusinessName(String businessName) {
        return isValidString(businessName, 3); // Minimum length of 3 for business name
    }
    public static boolean isValidJobCode(String jobCode) {

        return isValidString(jobCode, 4); // Minimum length of 4 for job code
    }
    //check password value matches with confirm password value
    public static boolean isValidMatch(String password, String c_password){
        return password.equals(c_password);
    }

    // Generic method to validate any string attribute based on minimum length
    private static boolean isValidString(String attribute, int minLength) {
        return attribute != null && attribute.length() >= minLength;
    }

}
