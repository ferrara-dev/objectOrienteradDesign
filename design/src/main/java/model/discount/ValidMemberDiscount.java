package model.discount;

import sequence.ListSequence;
import sequence.ListSequenceIterator;
import util.exception.NotFoundException;

import java.util.ArrayList;

public class ValidMemberDiscount {
    private ListSequence <Discount> listSequence;

    public ValidMemberDiscount(ArrayList<Discount> discounts){
        this.listSequence = new ListSequence();
        listSequence.setItems(discounts);
    }

    public ListSequence<Discount> getListSequence() {
        return listSequence;
    }
}
