package webmail.pages;

import org.stringtemplate.v4.ST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by YuexingYin on 11/3/14.
 */
public class RegistPage extends Page {

    public RegistPage(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public ST body() {
        return templates.getInstanceOf("regist");
    }

    @Override
    public ST getTitle() {
        return new ST("Sign up page");
    }
}
