package com.ee.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

   /* The PASSWORD_REGEX is a regular expression that enforces the following password criteria:
    At least 8 characters long
    Contains at least one digit (0-9)
    Contains at least one lowercase letter (a-z)
    Contains at least one uppercase letter (A-Z)
    Contains at least one special character (@, #, $, %, ^, &, +, =)
    Does not contain whitespace*/

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$\n";

    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);

    public static boolean isValidPassword(String password) {
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
