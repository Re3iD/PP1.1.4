package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import jm.task.core.jdbc.util.Util.*;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    private static Session getSession(){
        return Util.getSessionFactory().openSession();
    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try(Session session = getSession()) {
            String sql = "CREATE TABLE IF NOT EXISTS users(ID BIGINT NOT NULL AUTO_INCREMENT,NAME VARCHAR(100),LAST_NAME VARCHAR(100),AGE INT,PRIMARY KEY (ID))";
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class);
            transaction.commit();
        }catch (Throwable err){
            transaction.rollback();
        }

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try(Session session = getSession()) {
            transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            session.createSQLQuery(sql).addEntity(User.class);
            transaction.commit();
        }catch(Throwable err){
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try(Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        }catch (Throwable err){
            transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            session.flush();
            transaction.commit();
        } catch (Throwable err) {
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> res = null;
        Transaction transaction = null;

        try(Session session = getSession()) {
            transaction = session.beginTransaction();
            String sql = "FROM users" + User.class.getSimpleName();
            res = session.createQuery(sql, User.class).getResultList();
            transaction.commit();

        }catch (Throwable err){
            transaction.rollback();
        }
        return res;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try(Session session = getSession()) {
            transaction = session.beginTransaction();
            String sql = "DELETE FROM users";
            session.createSQLQuery(sql).addEntity(User.class);
            transaction.commit();
        }catch (Throwable err){
            transaction.rollback();
        }
    }
}
