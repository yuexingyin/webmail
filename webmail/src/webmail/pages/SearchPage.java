package webmail.pages;

import org.stringtemplate.v4.ST;
import webmail.managers.ListManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by YuexingYin on 12/1/14.
 */
public class SearchPage extends Page {
    public SearchPage(HttpServletRequest request, HttpServletResponse response) {
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
            st=templates.getInstanceOf("searchPage");
            st.add("username",username);
            String accountName=request.getSession().getAttribute("accountName").toString();
            st.add("accountName",accountName);
            getTitle();
        }
        return st;
    }

    @Override
    public ST getTitle() {
        return null;
    }
}
