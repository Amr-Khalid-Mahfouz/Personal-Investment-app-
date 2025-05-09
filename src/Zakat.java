public class Zakat {
    private int zakatAmount;
    private String assetName;
    private int investmentDuration;
    private boolean isZakatPaid;

    public Zakat(String assetName, int investmentDuration) {
        this.assetName = assetName;
        this.investmentDuration = investmentDuration;
        this.isZakatPaid = false;
        this.zakatAmount = 0;
    }

    public void calculateTheZakat(int assetValue) {
        if (checkZakatRules()) {
            zakatAmount = (int)(assetValue * 0.025); // 2.5% zakat
            isZakatPaid = true;
        } else {
            zakatAmount = 0;
            isZakatPaid = false;
        }
    }

    public int getZakatAmount() {
        return zakatAmount;
    }

    public boolean checkZakatRules() {
        // Assuming investment must be held for at least 1 year to be zakat-eligible
        return investmentDuration >= 12;
    }

    public void reportGenerator() {
        System.out.println("Zakat Report:");
        System.out.println("Asset Name: " + assetName);
        System.out.println("Investment Duration (months): " + investmentDuration);
        System.out.println("Zakat Paid: " + (isZakatPaid ? "Yes" : "No"));
        System.out.println("Zakat Amount: " + zakatAmount);
    }

    // Getters and setters
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public void setInvestmentDuration(int investmentDuration) {
        this.investmentDuration = investmentDuration;
    }

    public String getAssetName() {
        return assetName;
    }

    public int getInvestmentDuration() {
        return investmentDuration;
    }

    public boolean isZakatPaid() {
        return isZakatPaid;
    }
}
