package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args)  {
        var userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Mark", "Zuckerberg", (byte) 39);
        userService.saveUser("Tim", "Cook", (byte) 63);
        userService.saveUser("Jeff", "Bezos", (byte) 59);
        userService.saveUser("Sergey", "Brin", (byte) 50);

        var users = userService.getAllUsers();
        users.forEach(System.out::println);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
