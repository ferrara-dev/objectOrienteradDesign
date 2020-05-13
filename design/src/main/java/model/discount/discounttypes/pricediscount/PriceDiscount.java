package model.discount.discounttypes.pricediscount;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.discount.Discount;
import model.discount.discountrule.pricediscountrule.PriceDiscountRule;
import model.discount.discounttypes.defaultdiscount.NoPriceDiscount;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value= TotalCostDiscount.class, name = "TotalCostDiscount"),
        @JsonSubTypes.Type(value= NoPriceDiscount.class, name = "NoPriceDiscount")
})
public interface PriceDiscount extends Discount {

    @Override
    PriceDiscountRule getDiscountRule();
    @Override
    public Double getRequirement();
}
