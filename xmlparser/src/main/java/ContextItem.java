public class ContextItem {
    private String itemName;
    private Double totalValue;

    ContextItem(String itemName, double totalValue) {
        this.itemName = itemName;
        this.totalValue = totalValue;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }
}
