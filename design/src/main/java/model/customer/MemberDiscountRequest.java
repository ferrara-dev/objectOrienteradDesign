package model.customer;
import integration.productdb.Product;
import model.discount.Discount;
import model.discount.discounttypes.itemdiscount.ItemDiscount;
import model.sale.Sale;

import java.util.ArrayList;

/**
 * Class representing a discount request
 */
public class MemberDiscountRequest {
    private final Member member;
    private final Sale currentSale;
    private ArrayList<Discount> validatedDiscounts;

    public MemberDiscountRequest(Member member){
        this.member = member;
        this.currentSale = member.getSaleInProgress();
    }

    /**
     * Getter, gets the member that has requested the discount
     * @return
     */
    public Member getMember() {
        return member;
    }

    /**
     * Getter, gets the sale that the member has requested a
     * discount on
     * @return
     */
    public Sale getCurrentSale() {
        return currentSale;
    }

    /**
     * Discounts that are valid to the customer are added to the
     * <code> validatedDiscounts </code> list
     * @param discount the discount that has been validated
     */
    public void addValidatedDiscounts(Discount discount){
        if(this.validatedDiscounts == null)
            validatedDiscounts = new ArrayList<>();
        if(discount != null)
        this.validatedDiscounts.add(discount);
    }

    public ArrayList<Discount> getValidatedDiscounts() {
        return validatedDiscounts;
    }
}
