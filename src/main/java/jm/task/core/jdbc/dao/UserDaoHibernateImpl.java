package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import jm.task.core.jdbc.util.Util.*;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users(ID BIGINT NOT NULL AUTO_INCREMENT,NAME VARCHAR(100),LAST_NAME VARCHAR(100),AGE INT,PRIMARY KEY (ID))";
        Session session = Util.getSession();
        session.createSQLQuery(sql).addEntity(User.class);
        Util.closeTransactionSession();
    }

    @Override
    public void dropUsersTable() {
        Util.openTransactionSession();
        Session session = Util.getSession();
        String sql = "DROP TABLE IF EXISTS users";
        session.createSQLQuery(sql).addEntity(User.class);
        Util.closeTransactionSession();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Util.openTransactionSession();
        Session session = Util.getSession();
        session.save(new User(name,lastName,age));
        Util.closeTransactionSession();
    }

    @Override
    public void removeUserById(long id) {
        Util.openTransactionSession();
        Session session = Util.getSession();
        User user = session.load(User.class,id);
        session.delete(user);
        session.flush();
        Util.closeTransactionSession();
    }

    @Override
    public List<User> getAllUsers() {
        Util.openTransactionSession();
        Session session = Util.getSession();
        String sql = "FROM users"+User.class.getSimpleName();
        List<User> res = session.createQuery(sql,User.class).getResultList();
        Util.closeTransactionSession();
        return res;
    }

    @Override
    public void cleanUsersTable() {
        Util.openTransactionSession();
        Session session = Util.getSession();
        String sql = "DELETE FROM users";
        session.createSQLQuery(sql).addEntity(User.class);
        Util.closeTransactionSession();
    }
}
