package webmail.entities;

/**
 * Created by YuexingYin on 11/23/14.
 */
public class Account {

    String accountName;
    String accountPass;
    String username;

    public Account(String accountName, String accountPass,String username) {
        this.accountName = accountName;
        this.accountPass = accountPass;
        this.username=username;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountPass() {
        return accountPass;
    }

    public String getUsername(){return username;}
}
