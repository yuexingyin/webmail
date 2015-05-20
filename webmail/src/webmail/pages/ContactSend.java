package webmail.pages;

import org.stringtemplate.v4.ST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by YuexingYin on 11/30/14.
 */
public class ContactSend extends Page{
    public ContactSend(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public ST body() {
        return null;
    }

    @Override
    public ST getTitle() {
        return null;
    }
}
