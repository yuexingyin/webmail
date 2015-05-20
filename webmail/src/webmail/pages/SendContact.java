package webmail.pages;

import org.stringtemplate.v4.ST;
import webmail.managers.AccountManager;
import webmail.managers.SendMailManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by YuexingYin on 11/30/14.
 */
public class SendContact extends Page {
    public SendContact(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void verify() {

    }

    @Override
    public ST body() {

        ST st=null;
        if(request.getSession().getAttribute("user")==null){
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            String username=request.getSession().getAttribute("user").toString();
            String accountName=request.getSession().getAttribute("accountName").toString();
            String contactName = request.getParameter("contactName");
            st = templates.getInstanceOf("sendContact");
            st.add("username",username);
            st.add("accountName",accountName);
            st.add("contactName", contactName);
        }
        return st;
    }

    @Override
    public ST getTitle() {
        return null;
    }
}
