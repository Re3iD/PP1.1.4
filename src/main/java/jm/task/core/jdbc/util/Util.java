package jm.task.core.jdbc.util;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


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

    private static final SessionFactory buildFactory(){
        SessionFactory state = null;
        try{
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url", "jdbc:mysql://<your-host>:<your-port>/<your-dbname>");

            //You can use any database you want, I had it configured for Postgres
            prop.setProperty("dialect", "org.hibernate.dialect.MySQL");

            prop.setProperty("hibernate.connection.username", "root");
            prop.setProperty("hibernate.connection.password", "1W2E3R4t5y6u");
            prop.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            prop.setProperty("show_sql", "true");
            state = new Configuration().addProperties(prop).buildSessionFactory();
        }catch(Throwable err){
            err.printStackTrace();
        }
        return state;
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
