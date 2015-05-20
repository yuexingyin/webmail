package webmail.pages;

import org.stringtemplate.v4.ST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by YuexingYin on 11/3/14.
 */
public class EmailPage extends Page{

    public EmailPage(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void verify() {
        if(request.getSession().getAttribute("user")==null){
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ST body() {

        ST st=templates.getInstanceOf("email");
        return st;
    }

    @Override
    public ST getTitle() {
        return new ST("Email Page");
    }

}
