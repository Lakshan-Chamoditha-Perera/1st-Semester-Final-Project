package lk.ijse.studentsmanagement.regex;

import java.util.regex.Pattern;

public class RegExPatterns {
    private static Pattern namePattern = Pattern.compile("^[a-zA-Z]{4,}$");
    private static Pattern idPattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
    private static Pattern emailPattern = Pattern.compile("(^[a-zA-Z0-9_.-]+)@([a-zA-Z]+)([\\.])([a-zA-Z]+)$");
    private static Pattern cityPattern = Pattern.compile("[a-zA-Z]{4,}$");
    private static Pattern mobilePattern = Pattern.compile("^(07)[0-9]{8}$");

    public static Pattern getMobilePattern() {
        return mobilePattern;
    }

    public static Pattern getCityPattern() {
        return cityPattern;
    }

    public static Pattern getEmailPattern() {
        return emailPattern;
    }

    public static Pattern getNamePattern() {
        return namePattern;
    }

    public static Pattern getIdPattern() {
        return idPattern;
    }
}
