package service.visitor;



public interface Visitor<E,Data> {
    void processElement(E element);
    void setData(Data data);
}
