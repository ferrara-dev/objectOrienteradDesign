package model.physicalobjects;

/**
 * Utility class representing general contact information to a business or person
 */

public class ContactInformation {
    private String email;
    private String phoneNumber;
    private String mailAddress;

    public ContactInformation(String email, String phoneNumber){
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getMailAdress() {
        return mailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAddress = mailAdress;
    }

    @Override
    public String toString() {
        return "ContactInformation : \n" +
                "email: " + email + '\n' +
                "phoneNumber : " + phoneNumber + '\n' +
                "mailAddress : " + mailAddress + '\n';
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
