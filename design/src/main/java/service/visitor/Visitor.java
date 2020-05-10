package service.visitor;


import model.ObservableModel;
import util.exception.notfoundexception.NotFoundException;

public interface Visitor<E,Data> extends ObservableModel {
    void processElement(E element);
    void setData(Data data);
}
