package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args)  {
        var userDaoJDBC = new UserDaoJDBCImpl();

        String name = "Ivan";
        String lastName = "Ivanov";
        byte age = 15;

        userDaoJDBC.saveUser(name, lastName, age);
    }
}
