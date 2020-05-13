package util.sequence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListSequence<T> implements Sequence<T> {
    private List<T> items = new ArrayList<T>();
    private SequenceIterator<T> sequenceIterator = new ListSequenceIterator<>(this);

    @Override
    public SequenceIterator<T> sequenceIterator() {
        return this.sequenceIterator;
    }

    @Override
    public void addItem(T t) {
        items.add(t);
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public void addItem(int index, T t) {

    }

}