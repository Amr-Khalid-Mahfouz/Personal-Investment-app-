import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Portfolio implements Serializable {
    private ArrayList<Asset> assets;
    private ArrayList<BankAccount> bankAccounts;
    private double totalValue;
    private ValidationImpl validator;

    public Portfolio() {
        this.assets = new ArrayList<Asset>();
        this.bankAccounts = new ArrayList<BankAccount>();
        this.totalValue = 0;
        this.validator = new ValidationImpl();
    }

    // Bank Account Methods
    public BankAccount addBankAccount(Scanner scanner) {
        // Get card number (as String)
        String cardNumber;
        while (true) {
            System.out.print("Enter card number (16 digits): ");
            cardNumber = scanner.nextLine().trim().replaceAll("\\s+", ""); // Remove spaces

            if (cardNumber.length() == 16 && validator.checkIfNumeric(cardNumber)) {
                break;
            } else {
                System.out.println("Invalid card number. Must be exactly 16 digits (numbers only).");
            }
        }

        // Rest of the method remains the same...
        String cardHolderName;
        while (true) {
            System.out.print("Enter card holder name: ");
            cardHolderName = scanner.nextLine().trim();
            if (validator.checkName(cardHolderName)) {
                break;
            } else {
                System.out.println("Invalid name. Only letters and spaces allowed.");
            }
        }

        // Get expiry date
        Date expiryDate = null;
        while (true) {
            System.out.print("Enter expiry date (yyyy-MM-dd): ");
            String expiryDateStr = scanner.nextLine();
            if (validator.checkDate(expiryDateStr)) {
                expiryDate = convertStringToDate(expiryDateStr);
                if (expiryDate.after(new Date())) {
                    break;
                } else {
                    System.out.println("Expiry date must be in the future.");
                }
            } else {
                System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
            }
        }

        // Get account owner ID
        String accountOwnerId;
        while (true) {
            System.out.print("Enter account owner ID: ");
            accountOwnerId = scanner.nextLine().trim();
            if (!accountOwnerId.isEmpty()) {
                break;
            } else {
                System.out.println("Account owner ID cannot be empty.");
            }
        }

        while (true) {
            String otp = TheBank.generateOTP();
            System.out.println("Enter your OTP: ");
            System.out.println("for convenience: " + otp);
            String ans = scanner.nextLine();
            if(ans.equals(otp)){break;}
        }

        // Create and add the bank account
        BankAccount newAccount = new BankAccount(cardNumber, cardHolderName, expiryDate, accountOwnerId);
        bankAccounts.add(newAccount);
        System.out.println("Bank account added successfully!");
        return newAccount;
    }

    public Asset get_asset(int index){
        return assets.get(index);
    }

    // Asset Methods
    public void addAsset(Asset newAsset) {
        assets.add(newAsset);
        calculateTotalValue();
    }

    public void removeAsset(String name) {
        Asset toRemove = searchForAsset(name);
        if (toRemove != null) {
            assets.remove(toRemove);
            System.out.println("Asset removed successfully.");
            calculateTotalValue();
        } else {
            System.out.println("Asset not found.");
        }
    }

    public Asset searchForAsset(String name) {
        for (Asset a : assets) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    // index is 0-based in the function so if user enters the the index as a 1-based index subtract 1 in the function-call
    public void sellFromAsset(double percentage, int index) {
        if (percentage > 100) {
            System.out.println("Can't sell over 100% of asset");
            return;
        }

        Asset asset = assets.get(index);
        float currentQuantity = asset.getQuantity();
        float amountToSell = (float) (currentQuantity * (percentage / 100.0));
        float remaining = currentQuantity - amountToSell;

        asset.updateAsset(asset.getName(), remaining,
                        asset.getPurchasePrice(),
                        asset.getAssetType(),
                        asset.IsItHalal());

        if (remaining <= 0) {
            asset.switchState(state.sold);
        }
        System.out.println("Successfully sold " + percentage + "% of " + asset.getName());
    }

    public double calculateTotalValue() {
        this.totalValue = 0;
        for (Asset a : assets) {
            this.totalValue += a.getPurchasePrice() * a.getQuantity();
        }
        return this.totalValue;
    }

    public void zakat_due_amount(){
        float zakat_amount = 0;
        for(Asset a : assets){
            if(a.IsItHalal()){
                zakat_amount += a.getQuantity() * a.getPurchasePrice() * 0.025;
            }
        }
        System.out.println("Your zakat due amount: " + zakat_amount + "$");
    }

    public void view_assets_for_settings(){
        System.out.println("Currently owned Assets: ");
        int count = 1;
        for(Asset a : assets){
            System.out.println(count + ") " + a);
        }
    }

    public void print_portfolio(){
        System.out.println("**************************** OWNED ASSETS ****************************");
        for(Asset a : assets){
            System.out.println(a);
        }        
        System.out.println("\n----------------------------------------------------------------------\n");
        System.out.println("**************************** LINKED BANK ACCOUNTS ****************************");
        for(BankAccount b : bankAccounts){
            System.out.println(b);
        }
        System.out.println("\n----------------------------------------------------------------------\n");
        calculateTotalValue();
        System.out.print("Total Value of Assets: ");
        System.out.println(this.totalValue);
    }

    // Helper Methods
    public Date convertStringToDate(String dateStr) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Date parsing error. Using current date as fallback.");
            return new Date();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Portfolio Summary:\n");
        sb.append("Assets: ").append(assets.size()).append("\n");
        sb.append("Bank Accounts: ").append(bankAccounts.size()).append("\n");
        calculateTotalValue();
        sb.append("Total Value: $").append(String.format("%.2f", totalValue)).append("\n");
        return sb.toString();
    }

    public double getTotalValue(){
        return this.totalValue;
    }
}