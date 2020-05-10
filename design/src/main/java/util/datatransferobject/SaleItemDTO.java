package util.datatransferobject;

public class SaleItemDTO implements DataTransferObject{
    private int itemId;
    private int quantity;

    public SaleItemDTO(int itemId, int quantity){
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }


}
