package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args)  {
        var userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.getAllUsers().forEach(System.out::println);
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.getAllUsers().forEach(System.out::println);
    }
}
