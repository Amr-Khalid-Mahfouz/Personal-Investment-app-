public interface Validation {
    boolean checkEmail(String email);
    boolean checkName(String name);
    boolean checkPassword(String password);
    boolean checkIfNumeric(String input);
    boolean checkDate(String date); // Format: yyyy-MM-dd
}