package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    private Connection connection = Util.getConnection();
    public void createUsersTable() {
        String sqlTable = "CREATE TABLE IF NOT EXISTS users(" +
                "ID BIGINT NOT NULL AUTO_INCREMENT," +
                "NAME VARCHAR(100)," +
                "LAST_NAME VARCHAR(100)," +
                "AGE INT," +
                "PRIMARY KEY (ID) )";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sqlTable);
        }catch(SQLException err){
            err.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (NAME, LAST_NAME, AGE) VALUES(?, ?, ?)";

        try (PreparedStatement preStat = connection.prepareStatement(sql)) {
            preStat.setString(1, name);
            preStat.setString(2, lastName);
            preStat.setByte(3, age);

            preStat.executeUpdate();

        } catch (SQLException err) {
            System.out.print("saveUser");
            err.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try(PreparedStatement preStat = connection.prepareStatement(sql)){
            preStat.setLong(1,id);
            preStat.executeUpdate();
        }catch (SQLException err){
            err.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT ID, NAME, LAST_NAME, AGE FROM users;";

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
            }
        } catch (SQLException err) {
            System.out.print("getUsers");
            err.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        }catch (SQLException err){
            err.printStackTrace();
        }
    }
}
