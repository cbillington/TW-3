package com.travelexperts.travelpackages.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 468364 on 4/9/2017.
 */

public class PhoneNumberValidator {


    public static void main(String[] args) {
        System.out.println("Phone number 1234567890 validation result: "+validatePhoneNumber("1234567890"));
        System.out.println("Phone number 123-456-7890 validation result: "+validatePhoneNumber("123-456-7890"));
        System.out.println("Phone number 123-456-7890 x1234 validation result: "+validatePhoneNumber("123-456-7890 x1234"));
        System.out.println("Phone number 123-456-7890 ext1234 validation result: "+validatePhoneNumber("123-456-7890 ext1234"));
        System.out.println("Phone number (123)-456-7890 validation result: "+validatePhoneNumber("(123)-456-7890"));
        System.out.println("Phone number 123.456.7890 validation result: "+validatePhoneNumber("123.456.7890"));
        System.out.println("Phone number 123 456 7890 validation result: "+validatePhoneNumber("123 456 7890"));
    }

    public static boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
        else return false;

    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

}
