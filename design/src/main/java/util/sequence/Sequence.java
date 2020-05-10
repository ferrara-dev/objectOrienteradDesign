package util.sequence;


import java.util.List;

public interface Sequence<T>{
    SequenceIterator<T> getSequenceIterator();
    void addItem(T t);
    public List<T> getItems();
    public void setItems(List<T> items);
    void addItem(int index, T t);
}
