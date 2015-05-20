package webmail.managers;

import webmail.connection.DatabaseConnector;
import webmail.entities.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by YuexingYin on 11/10/14.
 */
public class ReceiveEmailManager {

    private String username = null;
    private String password = null;
    private StringBuffer text=new StringBuffer();
    private StringBuffer html=new StringBuffer();

    public ReceiveEmailManager(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public void ReceiveSSLGmail() throws IOException, SQLException, InterruptedException {

        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("pop.gmail.com", 995);
        InputStream inputStream = sslSocket.getInputStream();
        OutputStream outputStream = sslSocket.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter writer = new PrintWriter(outputStream, true);
        String tmp[];
        //Shake hands
        System.out.println(reader.readLine());

        writer.println("USER " + username);
        System.out.println(reader.readLine());
        writer.println("PASS " + password);
        System.out.println(reader.readLine());

        //STAT
        writer.println("STAT");
        tmp = reader.readLine().split(" ");
        int num = Integer.parseInt(tmp[1]);//Get the number of emails in mailbox.

        Email email=null;
        for(int i=1;i<=num;i++) {
            email = getMessage(reader, writer, i);
            writer.println("DELE "+i);
            reader.readLine();
            //if (email.getTo().equals(username)) {
                storeMessage(email);//Store one email in database.
            //}
        }

        writer.println("QUIT");
    }

    public Email getMessage(BufferedReader reader, PrintWriter writer,int n) throws IOException {

        Email email=null;
        String from="";
        String to="";
        String subject="";
        Date date;
        StringBuffer stringBuffer = new StringBuffer();

        writer.println("RETR " + n);

        String content=reader.readLine();
        content=reader.readLine();

        while (!content.toLowerCase().equals(".")) {
            stringBuffer.append(content+"\r\n");
            content=reader.readLine();
        }
        stringBuffer.append("."+"\r\n");
        String string = stringBuffer.toString();
        Properties properties = new Properties();
        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage mimeMessage = null;
        try {
            mimeMessage = new MimeMessage(mailSession, new ByteArrayInputStream(string.getBytes()));
            from= MimeUtility.decodeText(mimeMessage.getFrom()[0].toString());
            InternetAddress internetAddress=new InternetAddress(from);
            subject=mimeMessage.getSubject();
            date=mimeMessage.getSentDate();
            getContent(mimeMessage);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd-HH-mm:ss:ms");
            String newDate=simpleDateFormat.format(date);

            to= MimeUtility.decodeText(mimeMessage.getRecipients(Message.RecipientType.TO)[0].toString());
            InternetAddress internetAddress1=new InternetAddress(to);


            email=new Email();
            email.setDate(newDate);
            email.setSubject(subject);
            email.setFrom(internetAddress.getAddress());
            email.setTo(internetAddress1.getAddress());
            email.setText(text.toString());
            email.setHtml(html.toString());
            text.setLength(0);
            html.setLength(0);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return  email;
    }

    public void storeMessage(Email email) throws SQLException, InterruptedException {

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
        preparedStatement.setString(8,"1");
        preparedStatement.setString(9,email.getTo());
        preparedStatement.setBoolean(10,read);

        if(preparedStatement.executeUpdate()>0){
            System.out.println("Successful");
        }

        preparedStatement.close();
        db.close();
    }

    public void getContent(Part part) throws MessagingException, IOException {

        if(part.isMimeType("text/plain")){
            text.append(part.getContent().toString());
        }else if(part.isMimeType("text/html")){
            html.append(part.getContent().toString());
        }else  if(part.isMimeType("multipart/*")){
            Multipart multipart=(Multipart)part.getContent();
            int n=multipart.getCount();
            for(int i=0;i<n;i++){
                getContent(multipart.getBodyPart(i));
            }
        }
    }

//    public boolean isRead(MimeMessage mimeMessage) throws MessagingException {
//        boolean read=true;
//        Flags flags=mimeMessage.getFlags();
//        Flags.Flag[] flag=flags.getSystemFlags();
//        for(int i=0;i<flag.length;i++){
//            if(flag[i]==Flags.Flag.SEEN){
//                read=false;
//                break;
//            }
//        }
//        return  read;
//    }

    public void TestEmail() throws IOException {

        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("pop.gmail.com", 995);
        InputStream inputStream = sslSocket.getInputStream();
        OutputStream outputStream = sslSocket.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter writer = new PrintWriter(outputStream, true);

        //Shake hands
        System.out.println(reader.readLine());

        writer.println("USER " + username);
        System.out.println(reader.readLine());
        writer.println("PASS " + password);
        System.out.println(reader.readLine());

        //STAT
        writer.println("STAT");
        String tmp []= reader.readLine().split(" ");
        int num = Integer.parseInt(tmp[1]);//Get the number of emails in mailbox.

        //Print the content of all emails.
        for (int i = 1; i <= num; i++) {
            String content ="";
            writer.println("RETR " + i);
            while (true) {
                content=reader.readLine();
                System.out.println(content);
                if (content.toLowerCase().equals(".")) {
                    break;
                }
            }
        }
    }

}
