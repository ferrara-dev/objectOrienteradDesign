package service.visitor;


import model.ObservableModel;

public interface Visitor<E,Data> extends ObservableModel {
    void processElement(E element);
    void setData(Data data);
}
