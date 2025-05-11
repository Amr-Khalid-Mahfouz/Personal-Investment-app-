/**
 * Interface for validation methods related to user input such as email, name,
 * password, numeric checks, and date formatting.
 */
public interface Validation {
    /**
     * Validates if the given email is in a correct format.
     *
     * @param email The email string to validate.
     * @return true if valid, false otherwise.
     */
    boolean checkEmail(String email);

    /**
     * Validates if the name contains only letters and spaces.
     *
     * @param name The name string to validate.
     * @return true if valid, false otherwise.
     */
    boolean checkName(String name);

    /**
     * Validates if the password meets defined criteria.
     *
     * @param password The password string to validate.
     * @return true if valid, false otherwise.
     */
    boolean checkPassword(String password);

    /**
     * Checks whether the input string consists only of numeric characters.
     *
     * @param input The string to check.
     * @return true if numeric, false otherwise.
     */
    boolean checkIfNumeric(String input);

    /**
     * Validates whether the date is in the format yyyy-MM-dd.
     *
     * @param date The date string to validate.
     * @return true if it matches the format, false otherwise.
     */
    boolean checkDate(String date); // Format: yyyy-MM-dd
}