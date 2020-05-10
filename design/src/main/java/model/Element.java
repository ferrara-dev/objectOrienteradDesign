package model;

import service.visitor.Visitor;
import util.exception.notfoundexception.NotFoundException;

public interface Element {
    void acceptVisitor(Visitor visitor) throws NotFoundException, Exception;
}
