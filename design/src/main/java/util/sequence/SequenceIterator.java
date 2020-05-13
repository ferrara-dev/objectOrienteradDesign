package util.sequence;

import java.util.Iterator;

public interface SequenceIterator<T>  {
    T getCurrentItem();
    boolean isOver();
    void nextItem();
    void firstItem();
    boolean hasNext();
    T getItem(int index);
    boolean itemEquals(T t);
}