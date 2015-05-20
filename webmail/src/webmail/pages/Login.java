package webmail.pages;

import org.stringtemplate.v4.ST;
import sun.misc.BASE64Encoder;
import webmail.entities.User;
import webmail.managers.ListManager;
import webmail.managers.UserManager;
import webmail.misc.VerifyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by YuexingYin on 10/31/14.
 */
public class Login extends Page {
    public Login(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void verify() {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null) {
            UserManager userManager = new UserManager();
            password = new BASE64Encoder().encode(request.getParameter("password").getBytes());
            User user = new User(username, password);
            try {
                if (userManager.login(user)) {
                    request.getSession().setAttribute("user", user.getName());
                    ListManager listManager=new ListManager();
                    String accountName=listManager.listAccount(username);
                    request.getSession().setAttribute("accountName",accountName);
                } else {
                    System.out.println("No!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (request.getSession().getAttribute("user") == null) {
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                response.sendRedirect("/mainpage");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ST body() {
        return null;
    }

    @Override
    public ST getTitle() {
        return new ST("Home page");
    }
}

