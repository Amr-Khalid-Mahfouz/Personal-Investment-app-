import java.util.UUID;

public class InvestorBuilder {
    String name;
    String userName;
    String email;
    String password;
    String id;

    public InvestorBuilder addName(String userName) {
        this.userName = userName;
        return this;
    }

    public InvestorBuilder addEmail(String email) {
        this.email = email;
        return this;
    }

    public InvestorBuilder addPassword(String password) {
        this.password = password;
        return this;
    }

    public InvestorBuilder addFullName(String name) {
        this.name = name;
        return this;
    }

    private void generateId() {
        this.id = UUID.randomUUID().toString();
    }

    public Investor createAnInvestor() {
        generateId();
        return new Investor(this);
    }
}
