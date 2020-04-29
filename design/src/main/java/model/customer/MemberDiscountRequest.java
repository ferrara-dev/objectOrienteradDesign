package model.customer;
import model.discount.Discount;
import model.sale.Sale;

import java.util.ArrayList;


public class MemberDiscountRequest extends CustomerRequest{
    private final Member member;
    private final Sale currentSale;
    private ArrayList<Discount> validatedDiscounts;

    public MemberDiscountRequest(Member member){
        this.member = member;
        this.currentSale = member.getSaleInProgress();
    }

    public Member getMember() {
        return member;
    }

    public Sale getCurrentSale() {
        return currentSale;
    }

    public void setRequestedDiscounts(ArrayList<Discount> requestedDiscounts) {
        this.validatedDiscounts = requestedDiscounts;
    }

    public void addRequestedDiscounts(Discount discount){
        if(this.validatedDiscounts == null)
            validatedDiscounts = new ArrayList<>();
        if(discount != null)
        this.validatedDiscounts.add(discount);
    }

    public ArrayList<Discount> getValidatedDiscounts() {
        return validatedDiscounts;
    }
}
