import java.io.Serializable;
import java.util.ArrayList;

class Portfolio implements Serializable{
    private ArrayList<Asset> assets;
    // private Zakat zakat;
    private double totalValue;

    public Portfolio(){
        this.assets = new ArrayList<Asset>();
        this.totalValue = 0;
        // this.zakat = new Zakat();
    }

    public void addAsset(Asset new_Asset){
        assets.add(new_Asset);
    }

    public void viewAssets(){
        System.out.println("Assets in Portfolio: \n");
        for(Asset a : assets){
            System.out.println(a);
        }
    }

    public void removeAsset(String name){
        Asset to_remove = searchForAsset(name);
        
        if(to_remove == null){return;}
        assets.remove(to_remove);
    }

    public Asset searchForAsset(String name){
        for(Asset a : assets){
            if(a.getName().equals(name)){
                return a;
            }
        }
        return null;
    }

    public void sellFromAsset(double percentage, String name){
        if(percentage > 100){
            System.out.println("Can't sell over 100% of asset");
            return;}
            
        Asset asset = searchForAsset(name);
        if(asset != null){
            float currentQuantity = asset.getQuantity();
            float amountToSell = (float) (currentQuantity * (percentage / 100.0));
            float remaining = currentQuantity - amountToSell;

            asset.updateAsset(asset.getName(), remaining, asset.getPurchasePrice(),asset.getAssetType(), asset.IsItHalal());

            if (remaining <= 0) {asset.switchState(state.sold);}
        }
        else{System.out.println("Asset not found");}
    }

    @Override
    public String toString(){
        StringBuffer portfolioString = new StringBuffer();
        portfolioString.append("Assets in Portfolio: \n");
        for(Asset a : assets){
            portfolioString.append(a.toString());
        }
        return new String(portfolioString);
    }
}