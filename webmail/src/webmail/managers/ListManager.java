package webmail.managers;

import webmail.connection.DatabaseConnector;
import webmail.entities.Contact;
import webmail.entities.Email;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by YuexingYin on 11/25/14.
 */
public class ListManager {

    private boolean flag=false;
    private DatabaseConnector databaseConnector=new DatabaseConnector();

    private PreparedStatement preparedStatement=null;

    public String listAccount(String username) throws SQLException, InterruptedException {
        Connection db=databaseConnector.getConnection();
        String accountName="";
        preparedStatement=db.prepareStatement("select accountName from Account where username=?");
        preparedStatement.setString(1,username);
        ResultSet rs = preparedStatement.executeQuery();
        while( rs.next() ) {
            // read the result set
            accountName=rs.getString("accountName");
        }
        preparedStatement.close();
        db.close();
        return  accountName;
    }

    public Email[] listMail(String accountName,String status) throws SQLException, InterruptedException {
        Connection db=databaseConnector.getConnection();
        String id="";
        String date="";
        String to="";
        String from="";
        String subject="";
        String content="";
        String text="";
        String html="";
        boolean read;
        ArrayList<Email> emailList=new ArrayList<Email>();
        preparedStatement=db.prepareStatement("SELECT emailId,date,subject,sender,receiver,text,html,read FROM Email WHERE accountName=? AND status=?");
        preparedStatement.setString(1,accountName);
        preparedStatement.setString(2,status);
        ResultSet rs = preparedStatement.executeQuery();
        while( rs.next() ) {
            // read the result set
            id=rs.getString("emailId");
            date=rs.getString("date");
            from=rs.getString("sender");
            to=rs.getString("receiver");
            subject=rs.getString("subject");
            text=rs.getString("text");
            html=rs.getString("html");
            if(text==null){
                content=html;
            }else {
                content=text;
            }
            read=rs.getBoolean("read");
            Email email=new Email();
            email.setId(id);
            email.setDate(date);
            email.setSubject(subject);
            email.setFrom(from);
            email.setTo(to);
            email.setContent(content);
            email.setRead(read);
            emailList.add(email);
        }
        int size=emailList.size();
        Email[] emails = (Email[])emailList.toArray(new Email[size]);
        preparedStatement.close();
        db.close();
        return  emails;
    }

    public Email listContent(String emailId) throws SQLException, InterruptedException {
        Connection db=databaseConnector.getConnection();
        Email email=null;
        String id="";
        String sender="";
        String subject="";
        String content="";
        String text="";
        String html="";
        preparedStatement=db.prepareStatement("SELECT emailId,sender,subject,text,html FROM Email WHERE emailId=?");
        preparedStatement.setString(1,emailId);

        ResultSet rs=preparedStatement.executeQuery();
        if(rs.next()){
            id=rs.getString("emailId");
            sender=rs.getString("sender");
            subject=rs.getString("subject");
            text=rs.getString("text");
            html=rs.getString("html");
            if(html==null || html.equals("")){
                content=text;
            }else {
                content=html;
            }
            email=new Email();
            email.setId(id);
            email.setFrom(sender);
            email.setSubject(subject);
            email.setContent(content);
        }
        preparedStatement.close();
        db.close();
        return email;
    }

    public Email listForward(String emailId) throws SQLException, InterruptedException {
        Connection db=databaseConnector.getConnection();
        Email email=null;
        String sender="";
        String subject="";
        String content="";
        String text="";
        preparedStatement=db.prepareStatement("SELECT sender,subject,text FROM Email WHERE emailId=?");
        preparedStatement.setString(1,emailId);

        ResultSet rs=preparedStatement.executeQuery();
        if(rs.next()){
            sender=rs.getString("sender");
            subject=rs.getString("subject");
            text=rs.getString("text");
            content=text;
            email=new Email();
            email.setFrom(sender);
            email.setSubject(subject);
            email.setContent(content);
        }
        preparedStatement.close();
        db.close();
        return email;
    }

    public Contact[] listContact(String accountName) throws SQLException {
        Connection db=databaseConnector.getConnection();
        String contactId="";
        String contactName="";
        ArrayList<Contact> contactList=new ArrayList<Contact>();
        preparedStatement=db.prepareStatement("SELECT contactId,contactName FROM Contact WHERE accountName=?");
        preparedStatement.setString(1,accountName);
        ResultSet rs = preparedStatement.executeQuery();
        while( rs.next() ) {
            // read the result set
            contactId=rs.getString("contactId");
            contactName=rs.getString("contactName");
            Contact contact=new Contact();
            contact.setContactId(contactId);
            contact.setContactName(contactName);
            contactList.add(contact);
        }
        int size=contactList.size();
        Contact[] contacts = (Contact[])contactList.toArray(new Contact[size]);
        preparedStatement.close();
        db.close();
        return  contacts;
    }

    public Email[] listBy(String column,String accountName,String status) throws SQLException, InterruptedException {
        Connection db=databaseConnector.getConnection();
        String id="";
        String date="";
        String to="";
        String from="";
        String subject="";
        String content="";
        String text="";
        String html="";
        boolean read;
        ArrayList<Email> emailList=new ArrayList<Email>();
        preparedStatement=db.prepareStatement("SELECT emailId,date,subject,sender,receiver,text,html,read FROM Email WHERE accountName=? AND status=? order by "+column+ " desc");
        preparedStatement.setString(1,accountName);
        preparedStatement.setString(2,status);
        //preparedStatement.setString(3,column);
        ResultSet rs = preparedStatement.executeQuery();
        while( rs.next() ) {
            // read the result set
            id=rs.getString("emailId");
            date=rs.getString("date");
            from=rs.getString("sender");
            to=rs.getString("receiver");
            subject=rs.getString("subject");
            text=rs.getString("text");
            html=rs.getString("html");
            if(text==null){
                content=html;
            }else {
                content=text;
            }
            read=rs.getBoolean("read");
            Email email=new Email();
            email.setId(id);
            email.setDate(date);
            email.setSubject(subject);
            email.setFrom(from);
            email.setTo(to);
            email.setContent(content);
            email.setRead(read);
            emailList.add(email);
        }
        int size=emailList.size();
        Email[] emails = (Email[])emailList.toArray(new Email[size]);
        preparedStatement.close();
        db.close();
        return  emails;
    }

    public Email[] listBySearchResult(String option,String accountName,String str) throws SQLException, InterruptedException {
        Connection db=databaseConnector.getConnection();
        String id="";
        String date="";
        String to="";
        String from="";
        String subject="";
        String content="";
        String text="";
        String html="";

        ArrayList<Email> emailList=new ArrayList<Email>();
        preparedStatement=db.prepareStatement("SELECT emailId,date,subject,sender,receiver,text,html,read FROM Email WHERE accountName=? AND "+option+" LIKE '%"+str+"%'");
        preparedStatement.setString(1,accountName);


        ResultSet rs = preparedStatement.executeQuery();
        while( rs.next() ) {
            // read the result set
            id=rs.getString("emailId");
            date=rs.getString("date");
            from=rs.getString("sender");
            to=rs.getString("receiver");
            subject=rs.getString("subject");
            text=rs.getString("text");
            html=rs.getString("html");
            if(text==null){
                content=html;
            }else {
                content=text;
            }

            Email email=new Email();
            email.setId(id);
            email.setDate(date);
            email.setSubject(subject);
            email.setFrom(from);
            email.setTo(to);
            email.setContent(content);
            emailList.add(email);
        }
        int size=emailList.size();
        Email[] emails = (Email[])emailList.toArray(new Email[size]);
        preparedStatement.close();
        db.close();
        return  emails;
    }

//    public Email[] getFolderName(String accountName,String folder) throws SQLException, InterruptedException {
//        Connection db=databaseConnector.getConnection();
//        String folderName="";
//        ArrayList<Email> emailList=new ArrayList<Email>();
//        preparedStatement=db.prepareStatement("SELECT folder FROM Email WHERE accountName=?");
//        preparedStatement.setString(1,accountName);
//        ResultSet rs = preparedStatement.executeQuery();
//        while( rs.next() ) {
//            // read the result set
//            folderName=rs.getString("folder");
//            Email email=new Email();
//            email.setFolder(folderName);
//            emailList.add(email);
//        }
//        int size=emailList.size();
//        Email[] emails = (Email[])emailList.toArray(new Email[size]);
//        preparedStatement.close();
//        db.close();
//        return  emails;
//    }

//    public Email[] getFolderMail(String accountName,String folder) throws SQLException {
//        Connection db=databaseConnector.getConnection();
//        String id="";
//        String date="";
//        String to="";
//        String from="";
//        String subject="";
//        String content="";
//        String text="";
//        String html="";
//
//        ArrayList<Email> emailList=new ArrayList<Email>();
//        preparedStatement=db.prepareStatement("SELECT emailId,date,subject,sender,receiver,text,html,read FROM Email WHERE accountName=? AND folder=?");
//        preparedStatement.setString(1,accountName);
//        preparedStatement.setString(2,folder);
//        ResultSet rs = preparedStatement.executeQuery();
//        while( rs.next() ) {
//            // read the result set
//            id=rs.getString("emailId");
//            date=rs.getString("date");
//            from=rs.getString("sender");
//            to=rs.getString("receiver");
//            subject=rs.getString("subject");
//            text=rs.getString("text");
//            html=rs.getString("html");
//            if(text==null){
//                content=html;
//            }else {
//                content=text;
//            }
//
//            Email email=new Email();
//            email.setId(id);
//            email.setDate(date);
//            email.setSubject(subject);
//            email.setFrom(from);
//            email.setTo(to);
//            email.setContent(content);
//            emailList.add(email);
//        }
//        int size=emailList.size();
//        Email[] emails = (Email[])emailList.toArray(new Email[size]);
//        preparedStatement.close();
//        db.close();
//        return  emails;
//    }

}
