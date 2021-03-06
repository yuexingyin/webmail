package webmail.pages;

import org.stringtemplate.v4.ST;
import webmail.managers.ContactManager;
import webmail.managers.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by YuexingYin on 11/30/14.
 */
public class AddContact extends Page {
    public AddContact(HttpServletRequest request, HttpServletResponse response) {
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
            String contactName=request.getParameter("contact");
            String accountName=request.getSession().getAttribute("accountName").toString();
            ContactManager contactManager=new ContactManager();
            contactManager.addContact(contactName,accountName);
            response.sendRedirect("/contactPage");
        }

    }
}
