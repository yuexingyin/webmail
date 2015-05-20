package webmail.pages;

import org.stringtemplate.v4.ST;
import webmail.managers.ListManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by YuexingYin on 11/30/14.
 */
public class ContactPage extends Page {
    public ContactPage(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
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
            st=templates.getInstanceOf("contact");
            st.add("username",username);
            String accountName=request.getSession().getAttribute("accountName").toString();
            st.add("accountName",accountName);
            ListManager listManager=new ListManager();
            try {
                st.add("contact",listManager.listContact(accountName));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getTitle();
        }
        return st;
    }

    @Override
    public ST getTitle() {
        return null;
    }
}
