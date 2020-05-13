package util.sequence;


import java.util.List;

public interface Sequence<T>{
    SequenceIterator<T> sequenceIterator();
    void addItem(T t);
    public List<T> getItems();
    public void setItems(List<T> items);
    void addItem(int index, T t);
}
