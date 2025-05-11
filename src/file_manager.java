import java.io.*;
import java.util.*;

/**
 * Manages file operations related to storing and retrieving Investor objects.
 */
class file_manager{
    public ArrayList<Investor> all_investors;
    private static final String INVESTOR_FILE = "Investors.txt";

    /**
     * Constructs a new FileManager with an empty list of investors.
     */
    public file_manager(){
        this.all_investors = new ArrayList<Investor>();
    }
    
    /**
     * Reads all investors from the file and loads them into the list.
     *
     * @throws Exception if the file does not exist or cannot be read.
     */
    @SuppressWarnings("unchecked")
    public void get_all_investors() throws Exception{
        File file = new File(INVESTOR_FILE);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        all_investors = (ArrayList<Investor>) ois.readObject();
        ois.close();
    }
    
    /**
     * Adds a new investor to the list and writes the updated list to the file.
     *
     * @param newInvestor The new Investor to add.
     * @throws Exception if the file cannot be written to.
     */
    public void add_new_investor(Investor new_Investor) throws Exception{
        File file = new File(INVESTOR_FILE);
        // some investors were saved before this one
        if(file.exists()){
            this.get_all_investors();
        }
        all_investors.add(new_Investor);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(all_investors);
        oos.close();
    }

    /**
     * Updates an existing investor's record in the list and writes the updated list to the file.
     *
     * @param updatedInvestor The investor with updated data.
     * @throws Exception if the file cannot be written to.
     */
    public void updateInvestor(Investor updatedInvestor) throws Exception {
        get_all_investors();
        for (int i = 0; i < all_investors.size(); i++) {
            if (all_investors.get(i).get_user_name().equals(updatedInvestor.get_user_name())) {
                all_investors.set(i, updatedInvestor);
                break;
            }
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(INVESTOR_FILE))) {
            oos.writeObject(all_investors);
        }
    }
}