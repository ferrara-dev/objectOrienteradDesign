package util.sequence;

import java.util.Iterator;

public interface SequenceIterator<T> extends Iterator<T> {
    T getCurrentItem();
    boolean isOver();
    void nextItem();
    void firstItem();
    boolean hasNext();
    T getItem(int index);
    boolean itemEquals(T t);
}