package by.art.gallery.service.validation;

import java.util.regex.Pattern;

public class Validator {
    private static final String PASSWORD_PATTERN = "^[\\S]{1,25}$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    private static final String TEXT_FIELD_REGISTRATION = "^[\\w ]{1,25}$";

    private static Pattern pattern;

    private Validator() {

    }

    public static boolean validateEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        return !email.matches(EMAIL_PATTERN);//Fix
    }

    public static boolean validatePassword(String password) {
        return !password.matches(PASSWORD_PATTERN);
    }

    public static boolean validateTextField(String string){
        return !string.matches(TEXT_FIELD_REGISTRATION);
    }
}
