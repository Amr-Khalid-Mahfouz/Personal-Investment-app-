import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.util.UUID;

/**
 * Represents an investor who owns a portfolio and can log in to the system.
 * Includes user credentials and profile management.
 */
public class Investor implements Serializable {
    private String name;
    private String userName;
    private String email;
    private String password;
    public  Portfolio userPortfolio;
    private String id;

    /**
     * Default constructor for Investor.
     */
    public Investor(){}

    /**
     * Constructs an Investor using the provided builder.
     *
     * @param builder The builder containing investor details.
     */
    public Investor(InvestorBuilder builder) {
        this.name = builder.name;
        this.userName = builder.userName;
        this.email = builder.email;
        this.password = builder.password;
        this.id = builder.id;
        this.userPortfolio = new Portfolio();
    }

    /**
     * Returns the username of the investor.
     *
     * @return The investor's username.
     */
    public String get_user_name(){
        return this.userName;
    }

    /**
     * Verifies login credentials using username or email.
     *
     * @param userName The input username or email.
     * @param password The input password.
     * @return True if credentials match; false otherwise.
     */
    public boolean log_in(String userName, String password) {
        return ( this.userName.equalsIgnoreCase(userName)|| this.email.equalsIgnoreCase(userName) ) && this.password.equals(password);
    }

    /**
     * Updates the investor's profile information.
     *
     * @param userName New username.
     * @param email    New email.
     * @param password New password.
     */
    public void updateProfile(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    /**
     * Checks if a given username matches the investor's username.
     *
     * @param userName The username to check.
     * @return True if it matches; false otherwise.
     */
    public boolean checkUserNameInData(String userName) {
        return this.userName.equals(userName);
    }

    /**
     * Checks if a given password matches the investor's password.
     *
     * @param password The password to check.
     * @return True if it matches; false otherwise.
     */
    public boolean checkPasswordInData(String password) {
        return this.password.equals(password);
    }

    /**
     * Builder class to construct an Investor instance.
     */
    public static class InvestorBuilder {
        private String name;
        private String userName;
        private String email;
        private String password;
        private String id;

        /**
         * Sets the username for the investor.
         *
         * @param userName The investor's username.
         * @return The current builder instance.
         */
        public InvestorBuilder addUserName(String userName) {
            this.userName = userName;
            return this;
        }

        /**
         * Sets the email for the investor.
         *
         * @param email The investor's email.
         * @return The current builder instance.
         */
        public InvestorBuilder addEmail(String email) {
            this.email = email;
            return this;
        }

        /**
         * Sets the password for the investor.
         *
         * @param password The investor's password.
         * @return The current builder instance.
         */
        public InvestorBuilder addPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Sets the full name for the investor.
         *
         * @param name The investor's full name.
         * @return The current builder instance.
         */
        public InvestorBuilder addFullName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Generates a unique ID for the investor.
         */
        private void generateId() {
            this.id = UUID.randomUUID().toString();
        }

        /**
         * Builds and returns an Investor instance.
         *
         * @return A new Investor object.
         */
        public Investor createAnInvestor() {
            generateId();
            return new Investor(this);
        }
    }
}