package sequence;

public class ListSequenceIterator<T> implements ProductSequenceIterator<T> {
    private ListSequence<T> list=null;

    public ListSequenceIterator(ListSequence<T> list) {
        super();
        this.list = list;
    }

    private int currentItemNo=0;

    @Override
    public T getCurrentItem() {
        return (this.list.getItems().get(currentItemNo));
    }

    @Override
    public boolean isOver() {
        if(currentItemNo==list.getItems().size()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void nextItem() {
        this.currentItemNo++;
    }

    @Override
    public void firstItem() {
        this.currentItemNo=0;
    }

    @Override
    public boolean contains(T SaleItem) {
        return false;
    }

}