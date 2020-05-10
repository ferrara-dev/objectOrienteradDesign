package util.sequence;

public interface ProductSequenceIterator<T> extends SequenceIterator<T> {

    T getCurrentItem();

    boolean isOver();

    void nextItem();

    void firstItem();

}
