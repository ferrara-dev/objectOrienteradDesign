package model.customer;

/**
 * Class representing a customer identification
 */
public class CustomerId {
    private String personalNumber;

    public CustomerId() {
    }
    /**
     * Get the personal number used to identify the customer
     */
    public String getPersonalNumber() {
        return personalNumber;
    }
    /**
     * Check if this <code>customerId</code> equals
     * another id.
     */
    public boolean equals(CustomerId customerId) {
        if (this.personalNumber == customerId.getPersonalNumber())
            return true;
        return false;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }
}
