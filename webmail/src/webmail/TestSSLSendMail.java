package webmail;

import webmail.managers.SendMailManager;

/**
 * Created by YuexingYin on 11/7/14.
 */
public class TestSSLSendMail {

    public static void main(String []args){
        String serverName="smtp.gmail.com";
        String sender="yyfyou1991@gmail.com";
        String receiver="usftestbystudent@gmail.com";
        String subject="Test";
        String content="this is a test";
        String username="yyfyou1991@gmail.com";
        String password="Ivan620321";

//        SendMailManager sendMailManager=new SendMailManager(serverName,sender,receiver,subject,content,username,password);
//
//        sendMailManager.SendSSLMail();
    }
}
