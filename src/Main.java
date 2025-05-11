import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Main class to manage user interactions with the portfolio management system.
 * Provides functionality for logging in, signing up, and managing assets, accounts, and zakat.
 */
public class Main {
    /**
     * Prints the main menu options for the user to choose from.
     */
    static public void print_options(){
        System.out.println("\n**************************** MAIN MENU ****************************");
        System.out.println("1) view my Portfolio"); // done  
        System.out.println("2) Add new Asset to Portfolio"); // done
        System.out.println("3) Add new bank Account"); // done
        System.out.println("4) check zakat due amount"); // done
        System.out.println("5) Sell from Asset"); // done
        System.out.println("6) Edit Asset"); // done
        System.out.println("7) Remove Asset"); // done
        System.out.println("8) Quit"); // done
    }

    /**
     * Main method for running the portfolio management system.
     * It facilitates user login, asset management, zakat calculations, and more.
     *
     * @param args Command-line arguments (not used).
     * @throws Exception if an error occurs during file reading/writing.
     */
    public static void main(String[] args) throws Exception{
        Scanner input  = new Scanner(System.in);
        TheBank the_bank = new TheBank();
        file_manager the_file_manager = new file_manager();
        ValidationImpl checker = new ValidationImpl();

        Investor current_investor = new Investor();

        System.out.println("**************************** WELCOME TO PERSONAL INVESTOR ****************************\n");
        System.out.println("Choose what you want to do?");
        System.out.println("1) log in"); // done
        System.out.println("2) sign up"); // done 
        
        int input1 = input.nextInt();
        input.nextLine();
        switch (input1) {
            case 1:
                current_investor = log_in(input, the_file_manager);
                break;
            case 2:
                current_investor = sign_up(input, the_file_manager, checker);
                break;
        }

        if (current_investor == null) {
            System.out.println("Exiting program...");
            return;
        }
        boolean quit = false;
        while(!quit){
            print_options();
            System.out.print("Enter your choice: ");
            int input2 = input.nextInt();
            input.nextLine();

            switch (input2) {
                case 1:
                    current_investor.userPortfolio.print_portfolio();
                    break;
                case 2:
                    Add_asset(input, current_investor, checker);
                    break;
                case 3:
                    the_bank.bankAccounts.add(current_investor.userPortfolio.addBankAccount(input));
                    break;
                case 4:
                    current_investor.userPortfolio.zakat_due_amount();
                    break;
                case 5:
                    sell_from_asset(input, current_investor);
                    break;
                case 6:
                    edit_asset(input, current_investor);
                    break;
                case 7:
                    remove_asset(input, current_investor);
                    break;
                case 8:
                    System.out.println("Good bye");
                    the_file_manager.updateInvestor(current_investor);
                    quit = true;
                    break;   
            }
        }
    }

    /**
     * Logs in an investor based on username/email and password.
     *
     * @param input Scanner object for user input.
     * @param fm file_manager object for managing investors' data.
     * @return The logged-in Investor object, or null if login fails.
     * @throws Exception if an error occurs during file reading.
     */
    static public Investor log_in(Scanner input, file_manager fm) throws Exception {
        fm.get_all_investors();
        String user_name, password;
        
        System.out.println("Enter user name or Email: ");
        user_name = input.nextLine();
        
        System.out.println("Enter password: ");
        password = input.nextLine();

        for(Investor i : fm.all_investors){
            if(i.log_in(user_name, password)){
                System.out.println("Logged in successfully");
                return i;
            }
        }
        System.out.println("ERROR: User name and password are not compatible");
        return null;
    }
   
    /**
     * Registers a new investor with provided personal details.
     *
     * @param input Scanner object for user input.
     * @param fm file_manager object for managing investors' data.
     * @param checker Validation object for input validation.
     * @return The newly created Investor object.
     * @throws Exception if an error occurs during file operations.
     */
    static public Investor sign_up(Scanner input, file_manager fm, ValidationImpl checker) throws Exception {
        Investor.InvestorBuilder builder = new Investor.InvestorBuilder();
        String user_name, name, password, email;
        
        // name 
        while(true){
            System.out.println("Enter your full name: ");
            name = input.nextLine();
            if(checker.checkName(name)){
                break;
            }
            else{System.out.println("Invalid name");}
        }
    
        // user name
            System.out.println("Enter your user name: ");
            user_name = input.nextLine();

        // email
        while(true){
            System.out.println("Enter your email: ");
           email = input.nextLine();
            if(checker.checkEmail(email)){
                break;
            }
            else{System.out.println("Invalid email");}
        } 
    
        // password       
        while(true){
            System.out.println("Enter your password: ");
            password = input.nextLine();
            if(checker.checkPassword(password)){
                break;
            }
            else{
                System.out.println("Invalid password");
                System.out.println("password should contain 8 characters minimum, at least one uppercase letter, one lowercase letter, one number");
            }
        }

        // make new Investor
        builder.addEmail(email).addFullName(name).addPassword(password).addUserName(user_name);
        Investor new_investor = builder.createAnInvestor();
        System.out.println("Welcome to our new Investor");
        fm.add_new_investor(new_investor);
        return new_investor;
    } 

    /**
     * Adds a new asset to the investor's portfolio.
     *
     * @param input Scanner object for user input.
     * @param inv The investor whose portfolio will be updated.
     * @param checker Validation object for input validation.
     * @throws Exception if an error occurs during asset creation.
     */
    static public void Add_asset(Scanner input, Investor inv, ValidationImpl checker) throws Exception {
        Asset.AssetBuilder builder = new Asset.AssetBuilder();
        String name, asset_type, halal;
        float quantity, purchase_price;
        boolean is_it_halal;
        
        System.out.print("Enter the new Asset's name: ");
        name = input.nextLine();
        
        System.out.print("Enter the quantity owned: ");
        quantity = input.nextFloat();
        input.nextLine();
        
        System.out.print("what is the price that you purchased one of this assets?: ");
        purchase_price = input.nextFloat();
        input.nextLine();

        System.out.print("Is it halal? (y/n): ");
        halal = input.nextLine().trim().toLowerCase();
        if(halal.equals("y")) {is_it_halal = true;}
        else {is_it_halal = false;}
    
        System.out.println("What is the type of the Asset?: ");
        asset_type = input.nextLine();
 
        System.out.print("Enter Purchase Date (yyyy-MM-dd): ");
        String purchase_date_string = input.nextLine();
        Date date;

        // check the date
        while(true){
            if(!checker.checkDate(purchase_date_string)){
                System.out.println("Invalid date");                
                System.out.print("Enter Purchase Date (yyyy-MM-dd): ");
                purchase_date_string = input.nextLine();  
                continue;        
            }
            if(inv.userPortfolio.convertStringToDate(purchase_date_string).after(new Date())){
                System.out.println("Invalid date: cannot have purchase date in the future");
                System.out.print("Enter Purchase Date (yyyy-MM-dd): ");
                purchase_date_string = input.nextLine();      
                continue;
            }

            // no problems with the date
            date = inv.userPortfolio.convertStringToDate(purchase_date_string);
            break;
        }
        
        // build the asset
        Asset new_asset = builder.SetAssetState()
                          .SetAssetType(asset_type)
                          .SetIsHalal(is_it_halal)
                          .SetName(name)
                          .SetQuantity(quantity)
                          .SetPurchasePrice(purchase_price)
                          .SetPurchaseDate(date)
                          .Build();

        inv.userPortfolio.addAsset(new_asset);
    } 

    /**
     * Sells a percentage of the selected asset from the investor's portfolio.
     *
     * @param input Scanner object for user input.
     * @param current_investor The investor selling the asset.
     */
    static public void sell_from_asset(Scanner input, Investor current_investor) {
        int sell_choise;
        System.out.println("Choose an Asset to sell from: ");
        current_investor.userPortfolio.view_assets_for_settings();
        sell_choise = input.nextInt();
        input.nextLine();
        System.out.print("Enter the percentage to sell from the asset: ");
        float perc = input.nextFloat();
        input.nextLine();
        current_investor.userPortfolio.sellFromAsset(perc, sell_choise-1);
    }
    
    /**
     * Allows the investor to edit an asset's details in the portfolio.
     *
     * @param input Scanner object for user input.
     * @param inv The investor whose asset will be edited.
     */
    static public void edit_asset(Scanner input, Investor inv) {
        int edit_choise;
        System.out.println("Choose an Asset to edit: ");
        inv.userPortfolio.view_assets_for_settings();
        edit_choise = input.nextInt();
        input.nextLine();
        Asset asset = inv.userPortfolio.get_asset(edit_choise-1);

        System.out.println("Select property to edit:");
        System.out.println("1) Name");
        System.out.println("2) Quantity");
        System.out.println("3) Purchase Price");
        System.out.println("4) Asset Type");
        System.out.println("5) Halal Status");
        System.out.println("6) Edit All");
        int choice = Integer.parseInt(input.nextLine());
        input.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                asset.updateAsset(input.nextLine(), asset.getQuantity(), asset.getPurchasePrice(), asset.getAssetType(), asset.IsItHalal());
                break;
            case 2:
                System.out.print("Enter new quantity: ");
                float quantity = Float.parseFloat(input.nextLine());
                asset.updateAsset(asset.getName(), quantity, asset.getPurchasePrice(), asset.getAssetType(), asset.IsItHalal());
                break;
            case 3:
                System.out.print("Enter new purchase price: ");
                float price = Float.parseFloat(input.nextLine());
                asset.updateAsset(asset.getName(), asset.getQuantity(), price, asset.getAssetType(), asset.IsItHalal());
                break;
            case 4:
                System.out.print("Enter new asset type: ");
                asset.updateAsset(asset.getName(), asset.getQuantity(), asset.getPurchasePrice(), input.nextLine(), asset.IsItHalal());
                break;
            case 5:
                System.out.print("Is it halal? (y/n): ");
                String ans = input.nextLine().toLowerCase().trim();
                boolean halal = ans.equals("y")? true : false;
                asset.updateAsset(asset.getName(), asset.getQuantity(), asset.getPurchasePrice(), asset.getAssetType(), halal);
                break;
            case 6:
                System.out.print("Enter new name: ");
                String name = input.nextLine();
                System.out.print("Enter new quantity: ");
                float q = input.nextFloat();
                input.nextLine();
                System.out.print("Enter new price: ");
                float p = input.nextFloat();
                input.nextLine();
                System.out.print("Enter new type: ");
                String type = input.nextLine();
                System.out.print("Is it halal? (y/n): ");
                boolean h = input.nextLine().toLowerCase().equals("y")? true : false;

                asset.updateAsset(name, q, p, type, h);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Removes the specified asset from the investor's portfolio.
     *
     * @param input Scanner object for user input.
     * @param inv The investor whose asset will be removed.
     */
    static public void remove_asset(Scanner input, Investor inv){
        System.out.print("Enter Asset name to remove: ");
        String name = input.nextLine();
        inv.userPortfolio.removeAsset(name); 
    }
}
