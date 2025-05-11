import java.io.Serializable;
import java.util.regex.Pattern;

public class ValidationImpl implements Validation, Serializable {

    @Override
    public boolean checkEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }

    @Override
    public boolean checkName(String name) {
        String regex = "^[A-Za-z\\s]+$";
        return Pattern.matches(regex, name);
    }

    @Override
    public boolean checkPassword(String password) {
        // Minimum 8 characters, at least one uppercase letter, one lowercase letter, one number
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        return Pattern.matches(regex, password);
    }

    @Override
    public boolean checkIfNumeric(String input) {
        String regex = "\\d+";
        return Pattern.matches(regex, input);
    }

    @Override
    public boolean checkDate(String date) {
        // yyyy-MM-dd format
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        return Pattern.matches(regex, date);
    }
}
