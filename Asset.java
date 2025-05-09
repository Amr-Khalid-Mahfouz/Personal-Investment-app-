import java.io.Serializable;
import java.util.Date;

enum state{
    New , frozen, sold, active;
}

class Asset implements Serializable{
    private String name;
    private float quantity;
    private float PurchasePrice;
    private Date PurchaseDate;
    private String assetType;
    private state assetState;
    private boolean isHalal;
    
    public Asset(AssetBuilder ag){
        this.name = ag.name;
        this.quantity = ag.quantity;
        this.PurchaseDate = ag.PurchaseDate;
        this.PurchasePrice = ag.PurchasePrice;
        this.assetState = ag.assetState;
        this.assetType = ag.assetType;
        this.isHalal = ag.isHalal;
    }

    public String getName(){return this.name;}
    public float getQuantity(){return this.quantity;}
    public Float getPurchasePrice(){return this.PurchasePrice;}
    public Date getPurchaseDate(){return this.PurchaseDate;}
    public String getAssetType(){return this.assetType;}
    public state getAssetState(){return this.assetState;}
    public boolean IsItHalal(){return this.isHalal;}

    public void updateAsset(String name, float quantity, float purchasePrice,String assetType, boolean isHalal) {
        this.name = name;
        this.quantity = quantity;
        this.PurchasePrice = purchasePrice;
        this.assetType = assetType;
        this.isHalal = isHalal;
    }

    public void switchState(state new_state){
        this.assetState = new_state;
    }

    @Override
    public String toString(){
        return "Name: " + name + ", Quantity: " + quantity + ", Purchase price: " + PurchasePrice + "$, Purchase date: " 
        + PurchaseDate.toString() + ", Asset type: " + assetType + ", is it halal: " + (isHalal?"Yes":"No") + "\n";
    }

    public static class AssetBuilder{
        private String name;
        private float quantity;
        private float PurchasePrice;
        private Date PurchaseDate;
        private String assetType;
        private state assetState;
        private boolean isHalal;
        
        public AssetBuilder SetName(String n){
            this.name = n;
            return this;
        }   
        public AssetBuilder SetQuantity(float q){
            this.quantity = q;
            return this;
        }   
        public AssetBuilder SetPurchasePrice(float pPrice){
            this.PurchasePrice = pPrice;
            return this;
        }   
        public AssetBuilder SetPurchaseDate(){
            this.PurchaseDate = new Date();
            return this;
        }   
        public AssetBuilder SetAssetState(){
            this.assetState = state.New;
            return this;
        }   
        public AssetBuilder SetAssetType(String type){
            this.assetType = type;
            return this;
        }   
        public AssetBuilder SetIsHalal(boolean h){
            this.isHalal = h;
            return this;
        }   
        public Asset Build(){
            return new Asset(this);
        }
    }
}