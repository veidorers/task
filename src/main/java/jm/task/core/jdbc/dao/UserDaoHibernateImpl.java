package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (var session = HibernateUtil.buildSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("""
                CREATE TABLE IF NOT EXISTS users (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
                    name VARCHAR(128),
                    last_name VARCHAR(128),
                    age TINYINT
                );
                """).executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (var session = HibernateUtil.buildSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (var session = HibernateUtil.buildSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            session.persist(new User(name, lastName, age));

            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (var session = HibernateUtil.buildSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            var user = session.get(User.class, id);
            if(user != null) {
                session.remove(user);
            }

            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (var session = HibernateUtil.buildSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            return session.createQuery("SELECT u FROM User u").getResultList();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (var session = HibernateUtil.buildSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            session.createNativeQuery("TRUNCATE users").executeUpdate();

            session.getTransaction().commit();
        }
    }
}
