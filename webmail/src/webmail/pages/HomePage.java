package webmail.pages;

import org.stringtemplate.v4.ST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomePage extends Page {
	public HomePage(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public void verify() {
        if(request.getSession().getAttribute("user")!=null){
            try {
                response.sendRedirect("/mainpage");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	@Override
	public ST body() {
		return templates.getInstanceOf("home");
	}

	@Override
	public ST getTitle() {
		return new ST("Home page");
	}
}
