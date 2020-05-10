package model.physicalobjects;


public class Receipt {
    private final Store store = new Store(
            new Address("STORE STREET","A CITY","A COUNTRY", " 19149"),
            new ContactInformation("store@storemail.com","077 777 77 77"));


    public Receipt()
    {
    }

}
