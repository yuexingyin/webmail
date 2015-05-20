package webmail.pages;

import org.stringtemplate.v4.ST;
import webmail.entities.User;
import webmail.managers.ListManager;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.sax.SAXTransformerFactory;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by YuexingYin on 11/3/14.
 */
public class MainPage extends Page {

    public MainPage(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public ST body(){
        ST st=null;
        if(request.getSession().getAttribute("user")==null){
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            ListManager listManager=new ListManager();
            ListManager listManager1=new ListManager();
            String username=request.getSession().getAttribute("user").toString();
            st=templates.getInstanceOf("mainpage");
            st.add("username",username);
            String accountName=request.getSession().getAttribute("accountName").toString();
            st.add("accountName",accountName);
            try {
                try {
                    st.add("contact",listManager.listContact(accountName));
                    st.add("email",listManager1.listMail(accountName,"1"));
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
    public ST getTitle(){
        return  new ST("Main Page");
    }

}
