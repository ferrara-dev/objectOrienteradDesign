package service;

public interface IntegrationService<Model> {
    void updateDB(Object object);
    Model getFromDB(Object object);
    boolean find(Object object);

}
