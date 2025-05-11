import java.util.Date;
import java.io.Serializable;

public class BankAccount implements Serializable{
    private String cardNumber; 
    private String cardHolderName;
    private Date expiryDate;
    private String accountOwnerId;
    private String OTP;

    public BankAccount(String cardNumber, String cardHolderName, Date expiryDate, String accountOwnerId) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.accountOwnerId = accountOwnerId;
    }

    @Override
    public String toString(){
        return "Card Holder Name: " + cardHolderName + ", Card Number: " + cardNumber + ", Expiry Date: " + expiryDate.toString()
        + ", Owner Id: " + accountOwnerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    // checks if expiry date is in the future
    public boolean checkCard() {
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