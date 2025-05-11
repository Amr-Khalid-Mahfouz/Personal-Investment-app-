import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Implementation of the Validation interface providing
 * input validation methods for email, name, password, numbers, and dates.
 */
public class ValidationImpl implements Validation, Serializable {

    /**
     * Validates if the provided email string matches standard email format.
     *
     * @param email The email string to validate.
     * @return true if the email is valid, false otherwise.
     */
    @Override
    public boolean checkEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }

    /**
     * Validates that the name contains only alphabetic characters and spaces.
     *
     * @param name The name string to validate.
     * @return true if the name is valid, false otherwise.
     */
    @Override
    public boolean checkName(String name) {
        String regex = "^[A-Za-z\\s]+$";
        return Pattern.matches(regex, name);
    }

    /**
     * Validates that the password has at least one lowercase letter,
     * one uppercase letter, one digit, and is at least 8 characters long.
     *
     * @param password The password string to validate.
     * @return true if the password meets the criteria, false otherwise.
     */
    @Override
    public boolean checkPassword(String password) {
        // Minimum 8 characters, at least one uppercase letter, one lowercase letter, one number
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        return Pattern.matches(regex, password);
    }

    /**
     * Checks if the input string consists only of digits.
     *
     * @param input The string to check.
     * @return true if the string is numeric, false otherwise.
     */
    @Override
    public boolean checkIfNumeric(String input) {
        String regex = "\\d+";
        return Pattern.matches(regex, input);
    }

    /**
     * Validates whether the input date string matches the format yyyy-MM-dd.
     *
     * @param date The date string to validate.
     * @return true if the date format is valid, false otherwise.
     */
    @Override
    public boolean checkDate(String date) {
        // yyyy-MM-dd format
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        return Pattern.matches(regex, date);
    }
}
