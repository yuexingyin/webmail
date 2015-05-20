package webmail;

import webmail.managers.SendMailManager;

/**
 * Created by YuexingYin on 11/7/14.
 */
public class TestSendMail {
    public static void main(String []args) throws InterruptedException {

        String serverName="smtp.gmail.com";
        String sender="yyfyou1991@gmail.com";
        String receiver="usftestbystudent@gmail.com";
        String password="Ivan620321";
        String subject="Test";
        String content="this is a test";

        SendMailManager sendMailManager=new SendMailManager(sender,receiver,subject,content,sender,password);
        sendMailManager.SendGMail();
    }
}
