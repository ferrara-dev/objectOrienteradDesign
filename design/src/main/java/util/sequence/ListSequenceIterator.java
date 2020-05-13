package util.sequence;


/**
 * Generic implementation of <code> SequenceIterator </code> interface.
 * Used to iterate over a sequence of objects.
 *
 * @param <T>
 */
public class ListSequenceIterator<T> implements ProductSequenceIterator<T> {
    private Sequence<T> list = null;

    public ListSequenceIterator(Sequence<T> list) {
        this.list = list;
    }

    public ListSequenceIterator() {

    }
    public void setList(Sequence<T> list) {
        this.list = list;
    }

    public void setCurrentItemNo(int currentItemNo) {
        this.currentItemNo = currentItemNo;
    }

    public int getCurrentItemNo() {
        return currentItemNo;
    }

    public Sequence<T> getList() {
        return list;
    }

    private int currentItemNo = 0;

    /**
     * Get the object that the iterator is currently
     * pointing to
     *
     * @return the item that the iterator is pointing to,
     *         return <code> null </code> if the pointer
     *         is out of bounds.
     */
    @Override
    public T getCurrentItem() {
        try {
            return (this.list.getItems().get(currentItemNo));
        }
        catch (IndexOutOfBoundsException ex){
            ex.getMessage();
            return null;
        }
    }

    /**
     * Check if the iterator has gone through the
     * whole sequence.
     * @return
     */
    @Override
    public boolean isOver() {
        if (currentItemNo == list.getItems().size()) {
            firstItem();
            return true;
        } else {
            return false;
        }
    }



    @Override
    public void nextItem() {
        this.currentItemNo++;
    }

    /**
     * Move the iterator to the first object
     * in the sequence
     */
    @Override
    public void firstItem() {
        this.currentItemNo = 0;
    }

    /**
     * Check if the util.sequence being iterated over
     * contans anything after the current item
     *
     * @return
     */
    @Override
    public boolean hasNext() {
        if (currentItemNo == list.getItems().size()) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * Get an object at an specific index
     * in the sequence.
     * if the index is out of bound
     * <code> null </code> is returned
     * @param index of the item
     * @return item at a <code> index </code>
     *         if index is out of bounds
     *         return <code> null </code>.
     */
    @Override
    public T getItem(int index) {
        try {
            return  list.getItems().get(index);
        } catch (IndexOutOfBoundsException ex){
            ex.getMessage();
            return null;
        }
    }

    /**
     * Check if an item in the sequence being iterated
     * over equals another item.
     * <p>
     * The object class being iterated over should
     * override its <code> equals method </code>
     * before this method is used.
     *
     * @param t the other object
     * @return true if they are equal
     */
    @Override
    public boolean itemEquals(T t) {
        if (t.getClass().equals(getCurrentItem().getClass())) {
            if (t.equals(getCurrentItem()))
                return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}