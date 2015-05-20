package webmail.connection;

import java.sql.*;
import org.sqlite.JDBC;

/**
 * Created by YuexingYin on 10/28/14.
 */
public class DatabaseConnector {

    String dbFile = "Mymail.db";
    long start;
    Connection db=null;


    public DatabaseConnector(){

        try {
            start = System.currentTimeMillis();
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection()
    {
        try {
            db = DriverManager.getConnection("jdbc:sqlite:"+dbFile);
            long stop = System.currentTimeMillis();
            System.out.printf("SQL exe time %1.1f minutes\n", (stop-start)/1000.0/60.0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

       return db;
    }

    public void close(){
        if ( db!=null ) {
            try {
                db.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
