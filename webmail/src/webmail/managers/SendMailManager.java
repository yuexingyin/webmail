package webmail.managers;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import sun.misc.BASE64Encoder;
import webmail.connection.DatabaseConnector;
import webmail.entities.Email;

/**
 * Created by YuexingYin on 11/6/14.
 */
public class SendMailManager {

    private String serverName=null;
    private String sender=null;
    private String receiver=null;
    private String subject=null;
    private String content=null;
    private String username=null;
    private String password=null;

    public SendMailManager(String serverName, String sender, String receiver, String subject, String content) {
        this.serverName = serverName;
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
    }

    public SendMailManager(String sender, String receiver, String subject, String content, String username, String password) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
        this.username = username;
        this.password = password;
    }

    public void SendUSFMail(){

        try {
            Socket socket=new Socket(serverName,25);
            InputStream inputStream=socket.getInputStream();
            OutputStream outputStream=socket.getOutputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer=new PrintWriter(outputStream,true);

            System.out.println(reader.readLine());

            //Shake hands
            writer.println("HELO "+"cs.usfca.edu");
            System.out.println(reader.readLine());

            //Send header
            writer.println("MAIL FROM: <"+sender+">");
            System.out.println(reader.readLine());
            writer.println("RCPT TO: <"+receiver+">");
            System.out.println(reader.readLine());

            //Send DATA
            writer.println("DATA");
            System.out.println(reader.readLine());
            writer.println("Subject: "+subject);
            writer.println("From: "+sender);
            writer.println("To: "+receiver);
            writer.println("Content-Type: text/plain; charset=UTF-8");
            writer.println();
            writer.println(content);
            writer.println(".");
            System.out.println(reader.readLine());

            //Quit
            writer.println("QUIT");
            System.out.println(reader.readLine());
            writer.close();
            reader.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SendGMail() throws InterruptedException {
        try {
            String user=new BASE64Encoder().encode(username.getBytes());
            String pass = password;
            SSLSocketFactory sslSocketFactory= (SSLSocketFactory)SSLSocketFactory.getDefault();
            SSLSocket sslSocket=(SSLSocket)sslSocketFactory.createSocket("smtp.gmail.com",465);
            InputStream inputStream=sslSocket.getInputStream();
            OutputStream outputStream=sslSocket.getOutputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer=new PrintWriter(outputStream,true);

            System.out.println(reader.readLine());

            //Shake hands
            writer.println("HELO "+serverName);
            System.out.println(reader.readLine());

            //AUTH LOGIN
            writer.println("AUTH LOGIN");
            System.out.println(reader.readLine());
            writer.println(user);
            System.out.println(reader.readLine());
            writer.println(pass);
            System.out.println(reader.readLine());

            //Send header
            writer.println("MAIL FROM: <"+sender+">");
            System.out.println(reader.readLine());
            writer.println("RCPT TO: <"+receiver+">");
            System.out.println(reader.readLine());

            //Send DATA
            writer.println("DATA");
            System.out.println(reader.readLine());
            writer.println("Subject: "+subject);
            writer.println("From: "+sender);
            writer.println("To: "+receiver);
            writer.println("Content-Type: text/plain; charset=UTF-8");
            writer.println();
            writer.println(content);
            writer.println("\r\n.\r\n");
            System.out.println(reader.readLine());

            //Quit
            writer.println("QUIT");
            System.out.println(reader.readLine());
            writer.close();
            reader.close();
            sslSocket.close();

            try {
                Date newDate=new Date();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE MMMM dd yyyy");
                String date=simpleDateFormat.format(newDate);
                Email email=new Email();
                email.setDate(date);
                email.setSubject(subject);
                email.setFrom(sender);
                email.setTo(receiver);
                email.setText(content);
                storeEmail(email);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void storeEmail(Email email) throws SQLException, InterruptedException {


        DatabaseConnector databaseConnector=new DatabaseConnector();
        Connection db=databaseConnector.getConnection();
        PreparedStatement preparedStatement;
        boolean read=false;
        preparedStatement = db.prepareStatement("insert into Email values (?,?,?,?,?,?,?,?,?,?,?)");

        preparedStatement.setString(2,email.getDate());
        preparedStatement.setString(3,email.getSubject());
        preparedStatement.setString(4,email.getFrom());
        preparedStatement.setString(5,email.getTo());
        preparedStatement.setString(6,email.getText());
        preparedStatement.setString(7,email.getHtml());
        preparedStatement.setString(8,"0");
        preparedStatement.setString(9,email.getFrom());
        preparedStatement.setBoolean(10,read);

        if(preparedStatement.executeUpdate()>0){
            System.out.println("Successful");
        }

        preparedStatement.close();
        db.close();
    }

    public void replyGMail() throws InterruptedException {
        try {
            String user=new BASE64Encoder().encode(username.getBytes());
            String pass = password;
            SSLSocketFactory sslSocketFactory= (SSLSocketFactory)SSLSocketFactory.getDefault();
            SSLSocket sslSocket=(SSLSocket)sslSocketFactory.createSocket("smtp.gmail.com",465);
            InputStream inputStream=sslSocket.getInputStream();
            OutputStream outputStream=sslSocket.getOutputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer=new PrintWriter(outputStream,true);

            System.out.println(reader.readLine());

            //Shake hands
            writer.println("HELO "+serverName);
            System.out.println(reader.readLine());

            //AUTH LOGIN
            writer.println("AUTH LOGIN");
            System.out.println(reader.readLine());
            writer.println(user);
            System.out.println(reader.readLine());
            writer.println(pass);
            System.out.println(reader.readLine());

            //Send header
            writer.println("MAIL FROM: <"+sender+">");
            System.out.println(reader.readLine());
            writer.println("RCPT TO: <"+receiver+">");
            System.out.println(reader.readLine());

            //Send DATA
            writer.println("DATA");
            System.out.println(reader.readLine());
            writer.println("Subject: "+subject);
            writer.println("From: "+sender);
            writer.println("To: "+receiver);
            writer.println("Content-Type: text/plain; charset=UTF-8");
            writer.println();
            writer.println(content);
            writer.println("\r\n.\r\n");
            System.out.println(reader.readLine());

            //Quit
            writer.println("QUIT");
            System.out.println(reader.readLine());
            writer.close();
            reader.close();
            sslSocket.close();

            try {
                Date newDate=new Date();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd-HH-mm:ss:ms");
                String date=simpleDateFormat.format(newDate);
                Email email=new Email();
                email.setDate(date);
                email.setSubject(subject);
                email.setFrom(sender);
                email.setTo(receiver);
                email.setText(content);
                updateEmail(email);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateEmail(Email email) throws SQLException {

        DatabaseConnector databaseConnector=new DatabaseConnector();
        Connection db=databaseConnector.getConnection();
        PreparedStatement preparedStatement;

        preparedStatement = db.prepareStatement("insert into Email values (?,?,?,?,?,?,?,?,?,?,?)");

        preparedStatement.setString(2,email.getDate());
        preparedStatement.setString(3,email.getSubject());
        preparedStatement.setString(4,email.getFrom());
        preparedStatement.setString(5,email.getTo());
        preparedStatement.setString(6,email.getText());
        preparedStatement.setString(7,email.getHtml());
        preparedStatement.setString(8,"0");
        preparedStatement.setString(9,email.getFrom());

        if(preparedStatement.executeUpdate()>0){
            System.out.println("Successful");
        }

        preparedStatement.close();
        db.close();
    }


}
