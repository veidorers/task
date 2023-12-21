package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;

public class Main {
    public static void main(String[] args)  {
        var session = HibernateUtil.buildSessionFactory().getCurrentSession();
        session.beginTransaction();
        System.out.println(session.get(User.class, 3L));
        session.getTransaction().commit();
    }
}
