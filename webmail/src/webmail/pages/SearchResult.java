package webmail.pages;

import org.stringtemplate.v4.ST;
import webmail.managers.ListManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by YuexingYin on 12/1/14.
 */
public class SearchResult extends Page {
    public SearchResult(HttpServletRequest request, HttpServletResponse response) {
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
            st=templates.getInstanceOf("searchResult");
            st.add("username",username);
            String accountName=request.getSession().getAttribute("accountName").toString();
            st.add("accountName",accountName);
            String str;
            String option=request.getParameter("option");
            String sender=request.getParameter("sender");
            String subject=request.getParameter("subject");
            if(sender==null){
                str=subject;
            }else {
                str=sender;
            }
            try {
                st.add("email",listManager.listBySearchResult(option,accountName,str));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
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
