package webmail.entities;

/**
 * Created by YuexingYin on 11/30/14.
 */
public class Contact {
    private String contactId;
    private String contactName;
    private String accountName;

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setCAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getContactId() {
        return contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public String getCAccountName() {
        return accountName;
    }
}
