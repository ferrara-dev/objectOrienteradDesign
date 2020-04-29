package factory;

public interface Factory <Created, data>{
    Created create(data obj);
}
