package jm.task.core.jdbc.util;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    // реализуйте настройку соеденения с БД
    public static final String USER_BD = "root";
    public static final String PASS_BD = "1W2E3R4t5y6u";
    public static final String URL_BD = "jdbc:mysql://localhost:3306/mysql";
    public static Connection connection;
    public static Connection getConnection(){
        try{
            connection = DriverManager.getConnection(URL_BD,USER_BD,PASS_BD);
        }catch (SQLException err){
            err.printStackTrace();
            throw new RuntimeException();
        }
        return connection;
    }
    public static void closeConnection(){
        try{
            connection.close();
        }catch (SQLException err){
            err.printStackTrace();
        }

    }

}
