import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.util.UUID;

public class Investor implements Serializable {
    private String name;
    private String userName;
    private String email;
    private String password;
    public  Portfolio userPortfolio;
    private String id;

    public Investor(){}

    public Investor(InvestorBuilder builder) {
        this.name = builder.name;
        this.userName = builder.userName;
        this.email = builder.email;
        this.password = builder.password;
        this.id = builder.id;
        this.userPortfolio = new Portfolio();
    }

    public String get_user_name(){
        return this.userName;
    }

    public boolean log_in(String userName, String password) {
        return ( this.userName.equalsIgnoreCase(userName)|| this.email.equalsIgnoreCase(userName) ) && this.password.equals(password);
    }

    // public static Investor signUp(InvestorBuilder builder) {
    //     return builder.createAnInvestor();
    // }

    public void updateProfile(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }


    public boolean checkUserNameInData(String userName) {
        return this.userName.equals(userName);
    }

    public boolean checkPasswordInData(String password) {
        return this.password.equals(password);
    }

    public static class InvestorBuilder {
        private String name;
        private String userName;
        private String email;
        private String password;
        private String id;

        public InvestorBuilder addUserName(String userName) {
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
}