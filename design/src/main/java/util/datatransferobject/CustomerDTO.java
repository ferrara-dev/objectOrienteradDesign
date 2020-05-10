package util.datatransferobject;


/**
 * Data transfer object used to initialize the customer database.
 */
public class CustomerDTO implements DataTransferObject {
    private String customerId;
    private String name;
    private String email;

    public CustomerDTO(String name, String customerId, String email)
    {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}
