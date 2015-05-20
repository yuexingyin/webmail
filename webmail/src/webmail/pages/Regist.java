package webmail.pages;

import org.stringtemplate.v4.ST;
import sun.misc.BASE64Encoder;
import webmail.entities.Account;
import webmail.entities.User;
import webmail.managers.AccountManager;
import webmail.managers.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by YuexingYin on 10/31/14.
 */
public class Regist extends Page{

    public Regist(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void verify(){
        UserManager userManager=new UserManager();
        AccountManager accountManager=new AccountManager();
        String username=request.getParameter("username");
        String password = new BASE64Encoder().encode(request.getParameter("password").getBytes());
        String gender=request.getParameter("gender");
        String age=request.getParameter("age");
        User user=new User(username,password,gender,age);
        String accountName=request.getParameter("accountName");
        String accountPass=new BASE64Encoder().encode(request.getParameter("accountPass").getBytes());
        Account account=new Account(accountName,accountPass,username);
        try {
            if(userManager.register(user) && accountManager.addAccount(account)){
                System.out.println("<h1>Yes!</h1>");
                response.sendRedirect("/");
            }else {
                System.out.println("<h1>sorry</h1>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ST body() {
           return null;
    }

    @Override
    public ST getTitle() {
        return null;
    }

    public void isExistLoginName(String username) throws Exception {
        UserManager userManager=new UserManager();
        if(username.length()==0){
            response.getWriter().write("Username can't not be empty!");
        }else if(userManager.checkUsername(username)){
            response.getWriter().write("Username exists!");
        }else{
            response.getWriter().write("success");
        }

    }
}
