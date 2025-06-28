package pdev.com.agenda.util;

import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private static final Pattern USERNAME_REGEX = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_REGEX.matcher(email).matches();
    }

    public static boolean isValidUserName(String userName) {
        return userName != null && USERNAME_REGEX.matcher(userName).matches();
    }

    public static boolean isValidCPF(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) return false;

        int sum = 0;
        for (int i = 0; i < 9; i++) sum += (cpf.charAt(i) - '0') * (10 - i);
        int firstDigit = 11 - (sum % 11);
        firstDigit = (firstDigit > 9) ? 0 : firstDigit;

        sum = 0;
        for (int i = 0; i < 10; i++) sum += (cpf.charAt(i) - '0') * (11 - i);
        int secondDigit = 11 - (sum % 11);
        secondDigit = (secondDigit > 9) ? 0 : secondDigit;

        return cpf.charAt(9) - '0' == firstDigit && cpf.charAt(10) - '0' == secondDigit;
    }
}
