package webmail.managers;

import webmail.connection.DatabaseConnector;
import webmail.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import sun.misc.BASE64Encoder;

/**
 * Created by YuexingYin on 10/28/14.
 */
public class UserManager{

    private boolean flag=false;
    private DatabaseConnector databaseConnector=new DatabaseConnector();
    private PreparedStatement preparedStatement=null;


    public boolean register(User user)throws Exception{
        Connection db=databaseConnector.getConnection();

        preparedStatement = db.prepareStatement("insert into User values (?,?,?,?,?)");
        preparedStatement.setString(1, user.getUserid());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getGender());
        preparedStatement.setString(5, user.getAge());

        if(preparedStatement.executeUpdate()>0){
            flag=true;
        }
        preparedStatement.close();
        db.close();
        return flag;
    }

    public boolean login(User user)throws  Exception{
        Connection db=databaseConnector.getConnection();

        preparedStatement=db.prepareStatement("select * from User where username=? and password=?");
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getPassword());

        ResultSet resultSet=preparedStatement.executeQuery();

        if(resultSet.next()){
            flag=true;
        }else{
            System.out.println("No");
        }
        preparedStatement.close();
        db.close();

        return  flag;
    }

//    public String MD5(String password)throws NoSuchAlgorithmException,UnsupportedEncodingException{
//
//        MessageDigest md5=MessageDigest.getInstance("MD5");
//        BASE64Encoder base64en = new BASE64Encoder();
//        String string=base64en.encode(md5.digest(password.getBytes("utf-8")));
//        return string;
//    }

    public boolean changePassword(String username,String password) throws SQLException {
        Connection db=databaseConnector.getConnection();
        String newPass=new BASE64Encoder().encode(password.getBytes());
        preparedStatement=db.prepareStatement("UPDATE User SET password=? WHERE username=?");
        preparedStatement.setString(1,newPass);
        preparedStatement.setString(2,username);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        db.close();
        return flag;
    }

    public boolean checkUsername(String username) throws SQLException, InterruptedException {
        Connection db=databaseConnector.getConnection();
        preparedStatement=db.prepareStatement("select * from User where username=?");
        preparedStatement.setString(1,username);

        ResultSet resultSet=preparedStatement.executeQuery();

        if(resultSet.next()){
            flag=true;
        }
        preparedStatement.close();
        db.close();
        return flag;
    }
}
