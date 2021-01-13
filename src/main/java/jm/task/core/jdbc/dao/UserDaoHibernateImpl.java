package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Entity;
import java.sql.SQLData;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Session session;
    private Transaction tx;

    public UserDaoHibernateImpl() { }

    @Override
    public void createUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE testtable2 " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " PRIMARY KEY (id))" +
                    " name VARCHAR(30), " +
                    " lastName VARCHAR (30), " +
                    " Age VARCHAR (3), ");
            tx.commit();
            System.out.println("Таблица User-ов создана!");
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("drop table testtable2");
            query.executeUpdate();
            System.out.println("Таблица удалена...");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastname, byte Age) {
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(new User(name, lastname, Age));
            tx.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(session.get(User.class, id));
            tx.commit();
            System.out.println("Пользователь с id " + id + " удален!");
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            allUsers = session.createSQLQuery("SELECT * FROM testtable2").addEntity("name", User.class).list();
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            SQLQuery query;
            query = session.createSQLQuery("TRUNCATE Table testtable2");
            query.executeUpdate();
            tx.commit();
            System.out.println("Таблица очищена!");
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }

    }
}
