package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;


public class Main {
    public static void main(String[] args) {
       UserDaoHibernateImpl one= new UserDaoHibernateImpl();
       UserDaoHibernateImpl two = new UserDaoHibernateImpl();
       UserDaoHibernateImpl three = new UserDaoHibernateImpl();
       one.createUsersTable();
       one.saveUser("Egor", "Letov", (byte) 39);
       two.saveUser("Igor", "Smirnov", (byte) 66);
       three.saveUser("Irina", "Kirova", (byte) 25);
       two.removeUserById(2);
       two.getAllUsers();
       three.cleanUsersTable();
       one.dropUsersTable();
    }
}
