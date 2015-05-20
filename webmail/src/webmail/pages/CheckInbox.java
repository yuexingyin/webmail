package webmail.pages;

import org.stringtemplate.v4.ST;
import sun.misc.BASE64Decoder;
import webmail.managers.AccountManager;
import webmail.managers.ListManager;
import webmail.managers.ReceiveEmailManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by YuexingYin on 11/26/14.
 */
public class CheckInbox extends Page {

    private final static String CODE_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private final static int ORGINAL_LEN = 8;
    private final static int NEW_LEN = 6;

    public CheckInbox(HttpServletRequest request, HttpServletResponse response) {
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
            ListManager listManager1=new ListManager();
            AccountManager accountManager=new AccountManager();
            String username=request.getSession().getAttribute("user").toString();
            st=templates.getInstanceOf("mainpage");
            st.add("username",username);
            String accountName=request.getSession().getAttribute("accountName").toString();
            st.add("accountName",accountName);
            try {
                String[] info= new String[0];
                try {
                    info = accountManager.getAccountInfo(username);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    String password= null;
                    try {
                        password = decodeBase64(info[1]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ReceiveEmailManager receiveEmailManager=new ReceiveEmailManager(info[0],password);
                    try {
                        receiveEmailManager.ReceiveSSLGmail();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    st.add("email", listManager1.listMail(accountName, "1"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
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

    public static String decodeBase64(String encodeStr) throws Exception{
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < encodeStr.length(); i++){

            char c = encodeStr.charAt(i);
            int k = CODE_STR.indexOf(c);
            if(k != -1){
                String tmpStr = Integer.toBinaryString(k);
                int n = 0;
                while(tmpStr.length() + n < NEW_LEN){
                    n ++;
                    sb.append("0");
                }
                sb.append(tmpStr);
            }
        }

        int newByteLen = sb.length() / ORGINAL_LEN;

        byte[] b = new byte[newByteLen];
        for(int j = 0; j < newByteLen; j++){
            b[j] = (byte)Integer.parseInt(sb.substring(j * ORGINAL_LEN, (j+1) * ORGINAL_LEN),2);
        }

        return new String(b, "gb2312");
    }
}
