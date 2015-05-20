package webmail.pages;

import org.stringtemplate.v4.ST;
import webmail.managers.ListManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by YuexingYin on 11/22/14.
 */
public class SentMailPage extends Page {

    public SentMailPage(HttpServletRequest request, HttpServletResponse response) {
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
            ListManager listManager1=new ListManager();

            String username=request.getSession().getAttribute("user").toString();
            st=templates.getInstanceOf("sentMail");
            st.add("username",username);
            try {
                String accountName=request.getSession().getAttribute("accountName").toString();
                st.add("accountName",accountName);
                try {
                    st.add("email",listManager1.listMail(accountName,"0"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getTitle();
        }
        return st;
    }

    @Override
    public ST getTitle() {
        return new ST("Sent mail page");
    }
}
