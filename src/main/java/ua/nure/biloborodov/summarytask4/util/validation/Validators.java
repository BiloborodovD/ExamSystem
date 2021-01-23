package ua.nure.biloborodov.summarytask4.util.validation;

import java.util.regex.Pattern;

public class Validators {

    private static final Pattern EMAIL_PATTERN = Pattern
            .compile(
                    "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private static final Pattern LOGIN_PATTERN = Pattern
            .compile("^[a-z0-9_-]+$", Pattern.CASE_INSENSITIVE);
    private static final Pattern NAME_PATTERN = Pattern
            .compile("^\\p{L}+$", Pattern.CASE_INSENSITIVE);
    private static final Pattern TIME_PATTERN = Pattern.compile("[0-9]+");

    public static String validateLoginForm(String login, String password) {
        String message;
        message = validateLogin(login);
        message = validatePassword(password);
        return message;
    }

    public static String validateRegistrationForm(String login, String password, String firstName,
                                                  String lastName,
                                                  String email) {
        String message;
        if ((message = validateLogin(login)) != null) {
            return message;
        }
        if ((message = validatePassword(password)) != null) {
            return message;
        }
        if ((message = validateName(firstName)) != null) {
            return message;
        }
        if ((message = validateName(lastName)) != null) {
            return message;
        }
        if ((message = validateEmail(email)) != null) {
            return message;
        }
        return message;
    }


    private static String validateLogin(String login) {
        if (login == null || login.isEmpty()) {
            return "validate.login.cannot.be.empty";
        }
        if (!LOGIN_PATTERN.matcher(login).matches()) {
            return "validate.login.pattern.mismatch";
        }
        if (login.length() < 2 || login.length() > 100) {
            return "validate.length.from2to100";
        }
        return null;
    }

    private static String validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return "validate.email.name.cannot.be.empty";
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return "validate.email.pattern.mismatch";
        }
        if (email.length() < 5 || email.length() > 100) {
            return "validate.length.from5to100";
        }
        return null;
    }

    private static String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return "validate.password.cannot.be.empty";
        }
        if (password.contains(" ")) {
            return "validate.password.no.whitespace";
        }
        if (password.length() < 2 || password.length() > 100) {
            return "validate.length.from2to100";
        }
        return null;
    }

    private static String validateName(String name) {
        if (name == null || name.isEmpty()) {
            return "validate.name.cannot.be.empty";
        }
        if (!NAME_PATTERN.matcher(name).matches()) {
            return "validate.letters.only";
        }
        if (name.length() < 2 || name.length() > 100) {
            return "validate.length.from2to100";
        }
        return null;
    }

    private static String validateTime(String time) {
        if (time.length() == 0) {
            return "validate.field.cannot.be.empty";
        } else if (!TIME_PATTERN.matcher(time).matches()) {
            return "validate.numbers.only";
        }
        return null;
    }
}
