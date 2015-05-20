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
public class SortPage extends Page{
    public SortPage(HttpServletRequest request, HttpServletResponse response) {
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
            ListManager listManager1=new ListManager();
            String username=request.getSession().getAttribute("user").toString();
            st=templates.getInstanceOf("sortPage");
            st.add("username",username);
            String accountName=request.getSession().getAttribute("accountName").toString();
            st.add("accountName",accountName);
            try {
                try {
                    String column=request.getParameter("column");
                    st.add("contact",listManager.listContact(accountName));
                    st.add("email",listManager1.listBy(column,accountName,"1"));
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
        return null;
    }
}
