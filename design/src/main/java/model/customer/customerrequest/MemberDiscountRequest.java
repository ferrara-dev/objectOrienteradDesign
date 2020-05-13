package model.customer.customerrequest;
import model.customer.Member;
import model.discount.discountrule.DiscountRule;
import model.sale.saleinformation.SaleTransaction;
import util.sequence.ListSequence;

import java.util.ArrayList;

/**
 * Class representing a discount request
 */
public class MemberDiscountRequest {
    private final Member member;
    private SaleTransaction saleTransaction;
    private DiscountRule requestedDiscount;
    private ListSequence<DiscountRule> discountRuleListSequence;

    public ListSequence<DiscountRule> getDiscountRuleListSequence() {
        return discountRuleListSequence;
    }

    public void setSaleTransaction(SaleTransaction saleTransaction) {
        this.saleTransaction = saleTransaction;
    }

    public void setDiscountRuleListSequence(ArrayList<DiscountRule> discountRules) {
        this.discountRuleListSequence.setItems(discountRules);
    }

    public MemberDiscountRequest(Member member){
        this.member = member;
        discountRuleListSequence = new ListSequence<>();
    }

    public void setDiscountRuleListSequence(ListSequence<DiscountRule> discountRuleListSequence) {
        this.discountRuleListSequence = discountRuleListSequence;
    }

    public void setRequestedDiscount(DiscountRule requestedDiscount) {
        this.requestedDiscount = requestedDiscount;
    }

    public SaleTransaction getSaleTransaction() {
        return saleTransaction;
    }


    /**
     * Getter, gets the member that has requested the discount
     * @return
     */
    public Member getMember() {
        return member;
    }

    public void addDiscountRule(DiscountRule discountRule){
        discountRuleListSequence.addItem(discountRule);
    }
}
