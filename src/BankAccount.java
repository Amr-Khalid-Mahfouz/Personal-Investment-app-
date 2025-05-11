import java.util.Date;
import java.io.Serializable;


/**
 * Represents a bank account with card details and ownership information.
 * Includes OTP validation and expiry checking.
 */
public class BankAccount implements Serializable{
    private String cardNumber; 
    private String cardHolderName;
    private Date expiryDate;
    private String accountOwnerId;
    private String OTP;

    /**
     * Constructs a BankAccount with the provided card and owner details.
     *
     * @param cardNumber      The card number.
     * @param cardHolderName  The name of the card holder.
     * @param expiryDate      The card's expiry date.
     * @param accountOwnerId  The ID of the investor who owns this account.
     */
    public BankAccount(String cardNumber, String cardHolderName, Date expiryDate, String accountOwnerId) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.accountOwnerId = accountOwnerId;
    }

    /**
     * Returns a string representation of the bank account.
     *
     * @return A string describing the bank account details.
     */
    @Override
    public String toString(){
        return "Card Holder Name: " + cardHolderName +
        ", Card Number: " + cardNumber + 
        ", Expiry Date: " + expiryDate.toString()+
        ", Owner Id: " + accountOwnerId;
    }

    /**
     * @return The card number.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Checks if the card is still valid (not expired).
     *
     * @return True if the expiry date is in the future; false otherwise.
     */
    public boolean checkCard() {
        return expiryDate.after(new Date());
    }

    /**
     * Validates the provided OTP against the stored OTP.
     *
     * @param inputOTP The input OTP to verify.
     * @return True if the OTP matches; false otherwise.
     */
    public boolean checkTheOTP(String inputOTP) {
        return OTP != null && OTP.equals(inputOTP);
    }

    /**
     * Sets or updates the OTP for the account.
     *
     * @param OTP The OTP value to set.
     */
    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    /**
     * Updates the expiry date of the card.
     *
     * @param expiryDate The new expiry date.
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return The account owner's ID.
     */
    public String getAccountOwnerId() {
        return accountOwnerId;
    }

    /**
     * @return The name of the card holder.
     */
    public String getCardHolderName() {
        return cardHolderName;
    }

    /**
     * @return The expiry date of the card.
     */
    public Date getExpiryDate() {
        return expiryDate;
    }
}