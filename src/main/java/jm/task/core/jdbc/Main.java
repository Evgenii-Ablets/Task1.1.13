package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 20);
        userService.saveUser("Petr", "Petrov", (byte) 25);
        userService.saveUser("Sidor", "Sidorov", (byte) 30);
        userService.saveUser("Alexey", "Alexeev", (byte) 35);
        userService.getAllUsers().forEach(user -> System.out.println(user));
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeConnection();
    }
}
