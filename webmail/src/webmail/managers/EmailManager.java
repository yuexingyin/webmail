package webmail.managers;

import webmail.connection.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by YuexingYin on 11/28/14.
 */
public class EmailManager {


    private DatabaseConnector databaseConnector=new DatabaseConnector();
    private Connection db=databaseConnector.getConnection();
    private PreparedStatement preparedStatement=null;

    public void makeItTrash(String emailId) throws SQLException {

        preparedStatement=db.prepareStatement("UPDATE Email SET status='2' WHERE emailId=?");
        preparedStatement.setString(1,emailId);

        preparedStatement.executeUpdate();
        preparedStatement.close();
        db.close();

    }

    public void emptyTrash() throws SQLException {

        preparedStatement=db.prepareStatement("DELETE FROM Email WHERE status=2");
        preparedStatement.executeUpdate();
        preparedStatement.close();
        db.close();
    }

    public void setRead(String emailId) throws SQLException {
        boolean read=true;
        preparedStatement=db.prepareStatement("UPDATE Email SET read=? WHERE emailId=?");

        preparedStatement.setBoolean(1,read);
        preparedStatement.setString(2,emailId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        db.close();

    }
}
