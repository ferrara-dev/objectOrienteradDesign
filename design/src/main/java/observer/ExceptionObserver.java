package observer;


import model.exception.UserException;

public interface ExceptionObserver {
    void notifyUser(UserException e);
}
