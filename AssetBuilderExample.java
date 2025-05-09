public class AssetBuilderExample{
    public static void main(String[] args){
        Asset.AssetBuilder builder = new Asset.AssetBuilder();
        
        Asset a = builder.SetAssetState()
                  .SetAssetType("crypto")
                  .SetIsHalal(true)
                  .SetName("Bitcoin")
                  .SetPurchaseDate()
                  .SetPurchasePrice(1900)
                  .SetQuantity(20)
                  .Build();
        
        System.out.println(a.toString());

    }
}