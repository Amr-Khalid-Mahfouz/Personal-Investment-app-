import java.io.Serializable;
import java.util.ArrayList;

public class Investor implements Serializable {
    private String name;
    private String userName;
    private String email;
    private String password;
    private Portfolio userPortfolio;
    private String id;
    private ArrayList<BankAccount> bankAccounts;

    public Investor(InvestorBuilder builder) {
        this.name = builder.name;
        this.userName = builder.userName;
        this.email = builder.email;
        this.password = builder.password;
        this.id = builder.id;
        this.userPortfolio = new Portfolio();
        this.bankAccounts = new ArrayList<BankAccount>();        
    }

    public boolean logIn(String userName, String password) {
        return this.userName.equals(userName) && this.password.equals(password);
    }

    public static Investor signUp(InvestorBuilder builder) {
        return builder.createAnInvestor();
    }

    public void updateProfile(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void viewAssets() {
        userPortfolio.viewAssets();
    }

    public void viewCards() {
        for (BankAccount acc : bankAccounts) {
            String masked = "**** **** **** " + acc.getCardNumber().substring(12);
            System.out.println("Card: " + masked + " | Holder: " + acc.getCardHolderName());
        }
    }

    public boolean checkUserNameInData(String userName) {
        return this.userName.equals(userName);
    }

    public boolean checkPasswordInData(String password) {
        return this.password.equals(password);
    }
}
