package webmail.pages;

import org.stringtemplate.v4.ST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by YuexingYin on 11/5/14.
 */
public class LogOut extends Page {

    public LogOut(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void verify(){
        if(request.getSession().getAttribute("user")==null){
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            request.getSession().removeAttribute("user");
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ST body() {return null;}

    @Override
    public ST getTitle() {
        return null;
    }
}
