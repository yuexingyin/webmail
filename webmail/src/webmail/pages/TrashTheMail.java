package webmail.pages;

import org.stringtemplate.v4.ST;
import webmail.managers.AccountManager;
import webmail.managers.EmailManager;
import webmail.managers.SendMailManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by YuexingYin on 11/28/14.
 */
public class TrashTheMail extends Page {
    public TrashTheMail(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public ST body() {
        try {
            action();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ST getTitle() {
        return null;
    }

    public void action() throws SQLException, IOException {
        if(request.getSession().getAttribute("user")==null){
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            String emailId=request.getParameter("emailId1");
            EmailManager emailManager=new EmailManager();
            emailManager.makeItTrash(emailId);
            response.sendRedirect("/mainpage");
        }

    }
}
