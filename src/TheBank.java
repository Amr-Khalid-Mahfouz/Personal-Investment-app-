import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Represents a simplified bank system that manages bank accounts.
 */
public class TheBank implements Serializable{
    public List<BankAccount> bankAccounts;

    
    /**
     * Constructs a new instance of TheBank with an empty list of bank accounts.
     */
    public TheBank() {
        bankAccounts = new ArrayList<BankAccount>();
    }

    /**
     * Searches for a bank account by the account owner's ID.
     *
     * @param accountOwnerId The unique identifier of the account owner.
     * @return The BankAccount if found, otherwise null.
     */
    public BankAccount searchForUser(String accountOwnerId) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountOwnerId().equals(accountOwnerId)) {
                return account;
            }
        }
        return null;
    }

    /**
     * Adds a new bank account to the system.
     *
     * @param bankAccount The BankAccount object to add.
     */
    public void addBankAccount(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
    }

    /**
     * Extends the expiry date of a bank account based on the owner's ID.
     *
     * @param accountOwnerId The ID of the account owner.
     * @param newExpiryDate  The new expiry date to set.
     */
    public void extendExpiryDate(String accountOwnerId, Date newExpiryDate) {
        BankAccount account = searchForUser(accountOwnerId);
        if (account != null) {
            account.setExpiryDate(newExpiryDate);
        }
    }

    /**
     * Generates a 6-digit One-Time Password (OTP).
     *
     * @return A string representing a 6-digit OTP.
     */
    static public String generateOTP() {
            String otp = String.valueOf(100000 + new Random().nextInt(900000));
            // b.setOTP(otp);
            return otp;
    }
}
