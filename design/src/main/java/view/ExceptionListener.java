package view;
import observer.ExceptionEvent;

public interface ExceptionListener {
    void showException(ExceptionEvent exception);
}
