package sequence;

import java.util.Objects;

public interface SequenceIterator<T> {
    T getCurrentItem();
    boolean isOver();
    void nextItem();
    void firstItem();
    boolean contains(T object);
}