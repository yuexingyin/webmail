package webmail.pages;

import org.stringtemplate.v4.ST;
import sun.misc.BASE64Encoder;
import webmail.managers.AccountManager;
import webmail.managers.ListManager;
import webmail.managers.SendMailManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by YuexingYin on 11/25/14.
 */
public class Send extends Page {

    public Send(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void verify(){
        if(request.getSession().getAttribute("user")==null){
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                AccountManager accountManager=new AccountManager();
                String username=request.getSession().getAttribute("user").toString();
                String array[]= new String[0];
                try {
                    array = accountManager.getAccountInfo(username);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String sender=array[0];
                String user=array[0];
                String password=array[1];
                String to=request.getParameter("to");
                String subject=request.getParameter("subject");
                String content=request.getParameter("content");
                SendMailManager sendMailManager=new SendMailManager(sender,to,subject,content,user,password);
                try {
                    sendMailManager.SendGMail();
                    try {
                        response.sendRedirect("/mainpage");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public ST body() {return null;}

    @Override
    public ST getTitle() {
        return null;
    }
}
