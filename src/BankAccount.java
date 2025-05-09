import java.util.Date;

public class BankAccount {
    private String cardNumber;  // Changed from int to String
    private String cardHolderName;
    private Date expiryDate;
    private String accountOwnerId;
    private String OTP;

    // Updated constructor
    public BankAccount(String cardNumber, String cardHolderName, Date expiryDate, String accountOwnerId) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.accountOwnerId = accountOwnerId;
    }

    // Updated getter
    public String getCardNumber() {
        return cardNumber;
    }

    public boolean checkCard() {
        // Example implementation: check if expiry date is in the future
        return expiryDate.after(new Date());
    }

    public boolean checkTheOTP(String inputOTP) {
        return OTP != null && OTP.equals(inputOTP);
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Getters for necessary fields (optional, depending on usage)
    public String getAccountOwnerId() {
        return accountOwnerId;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
}
