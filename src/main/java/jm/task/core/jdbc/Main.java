package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args)  {
        var userDaoHibernate = new UserDaoHibernateImpl();

        userDaoHibernate.dropUsersTable();

        userDaoHibernate.createUsersTable();
        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("Mark", "Zuckerberg", (byte) 39);
        userDaoHibernate.saveUser("Tim", "Cook", (byte) 63);
        userDaoHibernate.saveUser("Jeff", "Bezos", (byte) 59);
        userDaoHibernate.saveUser("Sergey", "Brin", (byte) 50);

        userDaoHibernate.removeUserById(3L);
        userDaoHibernate.removeUserById(3L);

        var users = userDaoHibernate.getAllUsers();
        users.forEach(System.out::println);


        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.cleanUsersTable();

        userDaoHibernate.getAllUsers().forEach(System.out::println);

        userDaoHibernate.dropUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}
