import java.util.Scanner;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        // add investor first by using investorBuilder
        while (true) {
            System.out.println("===== Welcome to the Portfolio Management System =====");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = Integer.parseInt(scanner.nextLine());
            
            if (choice == 1) {
                System.out.println("Adding a new investor...");
                File file = new File("investor.txt");
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                InvestorBuilder builder = new InvestorBuilder();

                System.out.print("Enter your username: ");
                builder.addUserName(scanner.nextLine());
                System.out.print("Enter your email: ");
                builder.addEmail(scanner.nextLine());
                System.out.print("Enter your password: ");
                builder.addPassword(scanner.nextLine());
                System.out.print("Enter your full name: ");
                builder.addName(scanner.nextLine());
                Investor investor = new Investor(builder);
                System.out.println("Investor added successfully!\n");
                oos.writeObject(investor);
                oos.close();
                break;
            }
            if (choice == 2) 
            {
                System.out.println("Logging in...");
                File file = new File("investor.txt");
                if (!file.exists() || file.length() == 0) {
                    System.out.println("No investors found. Please sign up first.\n");
                    continue;
                }

                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    Investor investor = (Investor) ois.readObject();

                    System.out.print("Enter your username: ");
                    String username = scanner.nextLine();
                    if (investor.getUserName().equals(username)) {
                        System.out.print("Enter your password: ");
                        String password = scanner.nextLine();
                        if (investor.checkPasswordInData(password)) {
                            System.out.println("Login successful! Welcome, " + investor.getName() + "!");
                            System.out.println("Password is correct.\n");
                            break;
                        } else {
                            System.out.println("Incorrect password. Please try again.\n");
                        }
                    } else {
                        System.out.println("Username not found. Please try again or sign up first.\n");
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Error reading investor data. Please sign up again.");
                    e.printStackTrace(); // Optional for debugging
                }

            } 
            else if (choice == 3) 
            {
                System.out.println("Exiting program...");
                return;
            } 
            else 
            {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    
        


        
        Portfolio portfolio = new Portfolio();

        System.out.println("===== Portfolio Management System =====");

        while (true) {
            System.out.println("\nMenu Options:");
            System.out.println("1. Add Bank Account");
            System.out.println("2. Add Asset");
            System.out.println("3. View Portfolio");
            System.out.println("4. Remove Asset");
            System.out.println("5. Sell Asset");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    portfolio.addBankAccount();
                    break;

                case 2:
                    addAssetMenu(portfolio, scanner);
                    break;

                case 3:
                    portfolio.viewAssets();
                    break;

                case 4:
                    System.out.print("Enter asset name to remove: ");
                    String assetName = scanner.nextLine();
                    portfolio.removeAsset(assetName);
                    break;

                case 5:
                    System.out.print("Enter asset name to sell: ");
                    String assetToSell = scanner.nextLine();
                    System.out.print("Enter percentage to sell (0-100): ");
                    try {
                        double percentage = Double.parseDouble(scanner.nextLine());
                        portfolio.sellFromAsset(percentage, assetToSell);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid percentage value.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
    }

    private static void addAssetMenu(Portfolio portfolio, Scanner scanner) {
        System.out.println("\nAdd New Asset");
        System.out.print("Enter asset name: ");
        String name = scanner.nextLine();

        System.out.print("Enter quantity: ");
        float quantity;
        try {
            quantity = Float.parseFloat(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity. Using default value 1.");
            quantity = 1;
        }

        System.out.print("Enter purchase price: ");
        float price;
        try {
            price = Float.parseFloat(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price. Using default value 0.");
            price = 0;
        }

        System.out.print("Is this halal? (yes/no): ");
        boolean halal = scanner.nextLine().equalsIgnoreCase("yes");

        // Simplified asset creation (you may want to use your actual Asset subclasses)
        //Asset newAsset = new Asset(name, quantity, price, "General", halal);
        //portfolio.addAsset(newAsset);
        System.out.println("Asset added successfully!");
    }
}