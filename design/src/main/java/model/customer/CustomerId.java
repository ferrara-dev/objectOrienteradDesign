package model.customer;

public class CustomerId {
    private String personalNumber;

    public CustomerId() {
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public boolean equals(CustomerId customerId) {
        if (this.personalNumber == customerId.getPersonalNumber())
            return true;
        return false;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }
}
