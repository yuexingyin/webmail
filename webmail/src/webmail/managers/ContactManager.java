package webmail.managers;

import webmail.connection.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by YuexingYin on 11/30/14.
 */
public class ContactManager {

    private boolean flag=false;
    private DatabaseConnector databaseConnector=new DatabaseConnector();
    private PreparedStatement preparedStatement=null;

    public boolean addContact(String contactName,String accountName) throws SQLException {
        Connection db=databaseConnector.getConnection();
        preparedStatement = db.prepareStatement("insert into Contact values (?,?,?)");
        preparedStatement.setString(2,contactName);
        preparedStatement.setString(3, accountName);

        if(preparedStatement.executeUpdate()>0){
            flag=true;
        }
        preparedStatement.close();
        db.close();
        return  flag;
    }

    public boolean deleteContact(String contactId) throws SQLException {
        Connection db=databaseConnector.getConnection();
        preparedStatement = db.prepareStatement("DELETE FROM Contact WHERE contactId=?");
        preparedStatement.setString(1,contactId);

        if(preparedStatement.executeUpdate()>0){
            flag=true;
        }
        preparedStatement.close();
        db.close();
        return  flag;
    }


}
