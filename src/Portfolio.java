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
    private Validation validator;
    private double Al_Nisab = 85 * 5400;

    public Portfolio() {
        this.assets = new ArrayList<Asset>();
        this.bankAccounts = new ArrayList<BankAccount>();
        this.totalValue = 0;
        this.validator = new ValidationImpl();
    }

    // Bank Account Methods
    public void addBankAccount() {
        Scanner scanner = new Scanner(System.in);

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

        // Create and add the bank account
        BankAccount newAccount = new BankAccount(cardNumber, cardHolderName, expiryDate, accountOwnerId);
        bankAccounts.add(newAccount);
        System.out.println("Bank account added successfully!");
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

    public void viewAssets() {
        System.out.println("\nAssets in Portfolio:");
        if (assets.isEmpty()) {
            System.out.println("No assets found.");
        } else {
            for (Asset a : assets) {
                System.out.println(a);
            }
        }

        System.out.println("\nBank Accounts:");
        if (bankAccounts.isEmpty()) {
            System.out.println("No bank accounts found.");
        } else {
            for (BankAccount acc : bankAccounts) {
                String maskedNumber = "**** **** **** " + acc.getCardNumber().substring(12);
                System.out.println("Card: " + maskedNumber + " | Holder: " + acc.getCardHolderName());
            }
        }

        System.out.println("\nTotal Portfolio Value: $" + String.format("%.2f", totalValue));
    }

    public Asset searchForAsset(String name) {
        for (Asset a : assets) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    public void sellFromAsset(double percentage, String name) {
        if (percentage > 100) {
            System.out.println("Can't sell over 100% of asset");
            return;
        }

        Asset asset = searchForAsset(name);
        if (asset != null) {
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
            System.out.println("Successfully sold " + percentage + "% of " + name);
        } else {
            System.out.println("Asset not found");
        }
    }

    public double calculateTotalValue() {
        this.totalValue = 0;
        for (Asset a : assets) {
            this.totalValue += a.getPurchasePrice() * a.getQuantity();
        }
        return this.totalValue;
    }

    public void PayZakat(){
        // pay zakat
        if(totalValue >= Al_Nisab){
            for(Asset a : assets){
                if(a.IsItHalal()){
                    float new_quantity = (float)(a.getQuantity() - a.getQuantity() * 2.5);
                    a.updateAsset(a.getName(), new_quantity, a.getPurchasePrice(), a.getAssetType(), a.IsItHalal());
                }
            }
            System.out.println("Zakat is paid");
        }
        else {System.out.println("You don't have enough money");}
    }

    // Helper Methods
    private Date convertStringToDate(String dateStr) {
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
