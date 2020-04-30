package sequence;


import sequence.SequenceIterator;

public interface Sequence<T> {
    public SequenceIterator<T> createSeqIterator();
    boolean contains(T t);
}
