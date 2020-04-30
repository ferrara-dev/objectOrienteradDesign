package model.customer;
import integration.productdb.Product;
import model.amount.Cost;
import model.discount.Discount;
import model.discount.ValidMemberDiscount;
import model.discount.discountrule.DiscountRule;
import model.discount.discounttypes.itemdiscount.ItemDiscount;
import model.sale.Cart;
import model.sale.Sale;
import sequence.ListSequence;

import java.util.ArrayList;

/**
 * Class representing a discount request
 */
public class MemberDiscountRequest {
    private Cost saleCost;
    private Cart saleCart;
    private final Member member;
    private DiscountRule requestedDiscount;
    private ListSequence<DiscountRule> discountRuleListSequence;

    public ListSequence<DiscountRule> getDiscountRuleListSequence() {
        return discountRuleListSequence;
    }

    /**
     * Getter, gets the cart of saleItems that the customer has requested a discount on
     * that the member has requested a discount on
     * discount on
     * @return
     */
    public Cart getSaleCart() {
        return saleCart;
    }

    public Cost getSaleCost() {
        return saleCost;
    }

    public void setDiscountRuleListSequence(ArrayList<DiscountRule> discountRules) {
        this.discountRuleListSequence.setItems(discountRules);
    }

    public MemberDiscountRequest(Member member){
        this.member = member;
        this.saleCart = member.getSaleInProgress().getCart();
        this.saleCost = member.getSaleInProgress().getCost();
        discountRuleListSequence = new ListSequence<>();
    }


    public void setRequestedDiscount(DiscountRule requestedDiscount) {
        this.requestedDiscount = requestedDiscount;
    }

    /**
     * Get the currently requestedDiscount.
     * @return
     */
    public DiscountRule getRequestedDiscount() {
        return requestedDiscount;
    }

    /**
     * Getter, gets the member that has requested the discount
     * @return
     */
    public Member getMember() {
        return member;
    }
}
