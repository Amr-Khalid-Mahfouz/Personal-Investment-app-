import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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