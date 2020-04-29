package integration.productdb;

import util.datatransferobject.ItemDTO;

public class Product {
    private String name;
    private int itemId;
    private double price;
    private String category;
    private double totalVAT;
    private double taxRate;
    private double totalPrice;
    private int stockstatus;
    public Product(){

    }

    public void setAttributes(ItemDTO itemDTO){
        name = itemDTO.getName();
        itemId = itemDTO.getItemId();
        price = itemDTO.getPrice();
        category = itemDTO.getCategory();
        taxRate = itemDTO.getTax();
        stockstatus = itemDTO.getStockStatus();
        totalVAT = taxRate * price;
        totalPrice = totalVAT + price;
    }

    public void setStockstatus(int stockstatus) {
        this.stockstatus = stockstatus;
    }

    public int getStockstatus() {
        return stockstatus;
    }

    public double getTotalVAT() {
        return totalVAT;
    }

    public String getName() {
        return name;
    }

    public int getItemId() {
        return itemId;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getCategory() {
        return category;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public boolean equals(Product product){
        if(this == product)
            return true;
        else if(this.itemId == product.getItemId())
            return true;
        return false;
    }
}
