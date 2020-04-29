package util.datatransferobject;


public class DiscountDTO {
    private String type;
    private String requirement;
    private String reduction;
    private String limit;
    private String itemId;
    public String validMembers [];

    public DiscountDTO(String type, String requirement, String reduction, String limit, String itemId)
    {
        this.type = type;
        this.itemId = itemId;
        this.requirement = requirement;
        this.reduction = reduction;
        this.limit = limit;
    }


    public String getType() {
        return type;
    }

    public String getItemId(){
        return itemId;
    }

    public String getRequirement() {
        return requirement;
    }

    public String getReduction() {
        return reduction;
    }

    public String getLimit() {
        return limit;
    }
}
