package jm.task.core.jdbc.util;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String USER = "root";
    private static final String PASSWORD = "1W2E3R4t5y6u";

    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName(DRIVER);

        }catch(ClassNotFoundException err){
            err.printStackTrace();
        }

        try{
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (SQLException err){
            err.printStackTrace();
        }
        return connection;
    }
    public static void closeConnection(){
        try{
            getConnection().close();
        }catch (SQLException err){
            err.printStackTrace();
        }
    }
    private static final SessionFactory sessionFactory = buildFactory();
    private static Session session;
    private static Transaction transaction;

    private static final SessionFactory buildFactory(){
        SessionFactory state = null;
        try{
            state =  new Configuration().configure().buildSessionFactory();
        }catch(Exception err){
            err.printStackTrace();
        }
        return state;
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    public void closeSessionFactory(){
        try{
            getSessionFactory().close();
        }catch (Exception err){
            err.printStackTrace();
        }
    }
    public static Session getSession(){
        return session;
    }
    public Transaction getTransaction(){
        return transaction;
    }
    public static Session openSession(){
        return getSessionFactory().openSession();
    }
    public static Transaction openTransactionSession(){
        session = openSession();
        transaction = session.beginTransaction();
        return transaction;
    }
    public static void closeSession(){
        session.close();
    }
    public static void closeTransactionSession(){
        transaction.commit();
        closeSession();
    }

}
