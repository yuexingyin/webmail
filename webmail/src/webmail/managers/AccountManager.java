package webmail.managers;

import webmail.connection.DatabaseConnector;
import webmail.entities.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by YuexingYin on 11/23/14.
 */
public class AccountManager {

    private boolean flag=false;
    private DatabaseConnector databaseConnector=new DatabaseConnector();
    private PreparedStatement preparedStatement=null;

    public boolean addAccount(Account account) throws SQLException, InterruptedException {
        Connection db=databaseConnector.getConnection();
        preparedStatement = db.prepareStatement("insert into Account values (?,?,?,?)");
        preparedStatement.setString(2, account.getAccountName());
        preparedStatement.setString(3, account.getAccountPass());
        preparedStatement.setString(4,account.getUsername());

        if(preparedStatement.executeUpdate()>0){
            flag=true;
        }
        preparedStatement.close();
        db.close();
        return flag;
    }

    public String[] getAccountInfo(String username) throws SQLException, InterruptedException {
        Connection db=databaseConnector.getConnection();
        String accountName="";
        String password="";
        String[] array=new String[2];
        preparedStatement=db.prepareStatement("select accountName,accountPass from Account where username=?");
        preparedStatement.setString(1,username);
        ResultSet rs = preparedStatement.executeQuery();
        while( rs.next() ) {
            // read the result set
            accountName=rs.getString("accountName");
            password=rs.getString("accountPass");
            array[0]=accountName;
            array[1]=password;
        }
        preparedStatement.close();
        db.close();

        return array;
    }
}
