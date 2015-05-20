package webmail.pages;

import org.stringtemplate.v4.ST;
import webmail.managers.AccountManager;
import webmail.managers.SendMailManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by YuexingYin on 11/27/14.
 */
public class ReplyEmail extends Page {
    public ReplyEmail(HttpServletRequest request, HttpServletResponse response) {
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

                String username=request.getSession().getAttribute("user").toString();
                String array[]= new String[0];
                try {
                    AccountManager accountManager=new AccountManager();
                    array = accountManager.getAccountInfo(username);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String sender=array[0];
                String user=array[0];
                String password=array[1];
                String forward=request.getParameter("reply");
                String subject=request.getParameter("subject");
                String content=request.getParameter("replyContent");
                SendMailManager sendMailManager=new SendMailManager(sender,forward,subject,content,user,password);
                try {
                    sendMailManager.SendGMail();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    response.sendRedirect("/mainpage");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public ST body() {
        return null;
    }

    @Override
    public ST getTitle() {
        return null;
    }
}
