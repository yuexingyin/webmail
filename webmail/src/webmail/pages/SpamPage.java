package webmail.pages;

import org.stringtemplate.v4.ST;
import webmail.managers.ListManager;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by YuexingYin on 11/22/14.
 */
public class SpamPage extends Page{

    public SpamPage(HttpServletRequest request, HttpServletResponse response) {
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
        }else{
            ListManager listManager=new ListManager();
            String username=request.getSession().getAttribute("user").toString();
            st=templates.getInstanceOf("spam");
            st.add("username",username);
            String accountName=request.getSession().getAttribute("accountName").toString();
            st.add("accountName",accountName);
            getTitle();
        }
        return st;
    }

    @Override
    public ST getTitle() {
        return new ST("Spam page");
    }
}
