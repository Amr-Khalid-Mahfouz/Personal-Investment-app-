import java.io.Serializable;
import java.util.Date;

/**
 * Enum representing the state of an asset.
 */
enum state{
    New , frozen, sold, active;
}

/**
 * Represents a financial asset with details such as name, type, quantity, price, and halal status.
 * The asset also has a lifecycle state.
 */
class Asset implements Serializable{
    private String name;
    private float quantity;
    private float PurchasePrice;
    private Date PurchaseDate;
    private String assetType;
    private state assetState;
    private boolean isHalal;

    /**
     * Constructs an Asset using the AssetBuilder.
     *
     * @param builder The builder containing asset details.
     */
    public Asset(AssetBuilder ag){
        this.name = ag.name;
        this.quantity = ag.quantity;
        this.PurchaseDate = ag.PurchaseDate;
        this.PurchasePrice = ag.PurchasePrice;
        this.assetState = ag.assetState;
        this.assetType = ag.assetType;
        this.isHalal = ag.isHalal;
    }

    /**
     * @return The name of the asset.
     */
    public String getName(){
        return this.name;
    }

    /**
     * @return The quantity of the asset.
     */
    public float getQuantity(){
        return this.quantity;
    }

    /**
     * @return The purchase price of the asset.
     */
    public Float getPurchasePrice(){
        return this.PurchasePrice;
    }

    /**
     * @return The purchase date of the asset.
     */
    public Date getPurchaseDate(){
        return this.PurchaseDate;
    }

    /**
     * @return The type/category of the asset.
     */
    public String getAssetType(){
        return this.assetType;
    }

    /**
     * @return The current state of the asset.
     */
    public state getAssetState(){
        return this.assetState;
    }

    /**
     * @return True if the asset is halal; false otherwise.
     */
    public boolean IsItHalal(){
        return this.isHalal;
    }

    /**
     * Updates key asset details.
     *
     * @param name         New name of the asset.
     * @param quantity     New quantity.
     * @param purchasePrice New purchase price.
     * @param assetType    New type of asset.
     * @param isHalal      Updated halal status.
     */
    public void updateAsset(String name, float quantity, float purchasePrice,String assetType, boolean isHalal) {
        this.name = name;
        this.quantity = quantity;
        this.PurchasePrice = purchasePrice;
        this.assetType = assetType;
        this.isHalal = isHalal;
    }

    /**
     * Changes the current state of the asset.
     *
     * @param newState The new state to switch to.
     */
    public void switchState(state new_state){
        this.assetState = new_state;
    }

    /**
     * Returns a string representation of the asset.
     *
     * @return A detailed string describing the asset.
     */
    @Override
    public String toString(){
        return "Name: " + name +
            ", Quantity: " + quantity + 
            ", Purchase price: " + PurchasePrice + 
            "$, Purchase date: "+ PurchaseDate.toString() +
            ", Asset type: " + assetType +
            ", is it halal: " + (isHalal?"Yes":"No") + "\n";
    }

    /**
     * Builder class for constructing Asset objects.
     */
    public static class AssetBuilder{
        private String name;
        private float quantity;
        private float PurchasePrice;
        private Date PurchaseDate;
        private String assetType;
        private state assetState;
        private boolean isHalal;

        /**
         * Sets the asset name.
         *
         * @param n The asset name.
         * @return The current builder instance.
         */
        public AssetBuilder SetName(String n){
            this.name = n;
            return this;
        }

        /**
         * Sets the asset quantity.
         *
         * @param q The quantity.
         * @return The current builder instance.
         */
        public AssetBuilder SetQuantity(float q){
            this.quantity = q;
            return this;
        }

        /**
         * Sets the asset purchase price.
         *
         * @param pPrice The price.
         * @return The current builder instance.
         */
        public AssetBuilder SetPurchasePrice(float pPrice){
            this.PurchasePrice = pPrice;
            return this;
        }

        /**
         * Sets the purchase date.
         *
         * @param the_date The date of purchase.
         * @return The current builder instance.
         */
        public AssetBuilder SetPurchaseDate(Date the_date){
            this.PurchaseDate = the_date;
            return this;
        }

        /**
         * Initializes asset state to NEW.
         *
         * @return The current builder instance.
         */
        public AssetBuilder SetAssetState(){
            this.assetState = state.New;
            return this;
        }

        /**
         * Sets the asset type.
         *
         * @param type The Asset type/category.
         * @return The current builder instance.
         */
        public AssetBuilder SetAssetType(String type){
            this.assetType = type;
            return this;
        }

        /**
         * Sets the halal status.
         *
         * @param h True if halal; false otherwise.
         * @return The current builder instance.
         */
        public AssetBuilder SetIsHalal(boolean h){
            this.isHalal = h;
            return this;
        }

        /**
         * Builds and returns the Asset instance.
         *
         * @return A new Asset object.
         */
        public Asset Build(){
            return new Asset(this);
        }
    }
}