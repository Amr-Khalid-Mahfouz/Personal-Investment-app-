import java.io.*;
import java.util.*;

class file_manager{
    public ArrayList<Investor> all_investors;
    private static final String INVESTOR_FILE = "Investors.txt";

   
    public file_manager(){
        this.all_investors = new ArrayList<Investor>();
    }
    
    @SuppressWarnings("unchecked")
    public void get_all_investors() throws Exception{
        File file = new File(INVESTOR_FILE);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        all_investors = (ArrayList<Investor>) ois.readObject();
        ois.close();
    }
    
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