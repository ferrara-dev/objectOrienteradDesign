package sequence;

import java.util.ArrayList;
import java.util.List;

public class ListSequence<T> implements Sequence<T> {
    private List<T> items = new ArrayList<T>();

    @Override
    public SequenceIterator<T> createSeqIterator() {
        return new ListSequenceIterator<T>(this);
    }

    @Override
    public boolean contains(T t) {
        return false;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    //setter & getter for private list variable
}