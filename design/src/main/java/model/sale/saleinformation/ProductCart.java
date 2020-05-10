package model.sale.saleinformation;


import model.ObservableModel;
import model.sale.SaleItem;
import observer.EventObserver;
import observer.ObservedEvent;
import observer.PropertyChangeEvent;
import model.Element;
import service.visitor.Visitor;
import util.exception.notfoundexception.NotFoundException;
import util.sequence.ListSequenceIterator;
import util.sequence.Sequence;
import util.sequence.SequenceIterator;

import java.util.ArrayList;
import java.util.List;


public class ProductCart implements Sequence<SaleItem>, ObservableModel, Element {
    private ArrayList<EventObserver> eventObservers = new ArrayList<>();
    private List<SaleItem> items = new ArrayList<SaleItem>();
    private SequenceIterator<SaleItem> sequenceIterator = new ListSequenceIterator<SaleItem>(this);

    public ProductCart() {

    }

    /**
     * add a product to the list sequence
     * @param saleItem the item that is added to the sequence
     */
    @Override
    public void addItem(SaleItem saleItem) {
        notifyObservers(new PropertyChangeEvent("items", items, items));
        items.add(saleItem);
    }

    @Override
    public void addItem(int index, SaleItem saleItem) {
        notifyObservers(new PropertyChangeEvent("items", items, items));
        items.add(index,saleItem);
    }

    @Override
    public void notifyObservers(ObservedEvent observedEvent) {
        eventObservers.forEach(eventObserver -> {
            eventObserver.newEvent(observedEvent);
        });
    }

    @Override
    public void addObserver(EventObserver eventObserver) {
        eventObservers.add(eventObserver);
    }

    @Override
    public void removeObserver(EventObserver eventObserver) {

    }

    @Override
    public SequenceIterator<SaleItem> getSequenceIterator() {
        return sequenceIterator;
    }

    @Override
    public List<SaleItem> getItems() {
        return items;
    }

    @Override
    public void setItems(List<SaleItem> items) {
        this.items = items;
    }

    @Override
    public void acceptVisitor(Visitor visitor) throws NotFoundException {
        visitor.processElement(this);
    }

}