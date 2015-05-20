package webmail.pages;

import org.stringtemplate.v4.ST;
import webmail.managers.EmailManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by YuexingYin on 11/28/14.
 */
public class EmptyTrash extends Page {
    public EmptyTrash(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }


    @Override
    public ST body() {

        try {
            action();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ST getTitle() {
        return null;
    }

    public void action() throws SQLException, IOException {
        if(request.getSession().getAttribute("user")==null){
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            EmailManager emailManager=new EmailManager();
            emailManager.emptyTrash();
            response.sendRedirect("/trash");
        }

    }
}
