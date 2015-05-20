package webmail;

import webmail.managers.ReceiveEmailManager;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by YuexingYin on 11/10/14.
 */
public class TestSSLReceive {

    public static void main(String []args) throws IOException, SQLException {
        String ServerName="pop.gmail.com";
        String username="usftestbystudent@gmail.com";
        String password="testemailpassword";

        ReceiveEmailManager receiveEmailManager=new ReceiveEmailManager(username,password);
        receiveEmailManager.TestEmail();
    }
}
