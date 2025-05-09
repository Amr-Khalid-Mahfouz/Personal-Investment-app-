import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TheBank {
    private List<BankAccount> bankAccounts;

    public TheBank() {
        bankAccounts = new ArrayList<>();
    }

    public BankAccount searchForUser(String accountOwnerId) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountOwnerId().equals(accountOwnerId)) {
                return account;
            }
        }
        return null;
    }

    public void addBankAccount(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
    }

    public void extendExpiryDate(String accountOwnerId, Date newExpiryDate) {
        BankAccount account = searchForUser(accountOwnerId);
        if (account != null) {
            account.setExpiryDate(newExpiryDate);
        }
    }

    public void generateOTP(String accountOwnerId) {
        BankAccount account = searchForUser(accountOwnerId);
        if (account != null) {
            String otp = String.valueOf(100000 + new Random().nextInt(900000));
            account.setOTP(otp);
        }
    }
}
