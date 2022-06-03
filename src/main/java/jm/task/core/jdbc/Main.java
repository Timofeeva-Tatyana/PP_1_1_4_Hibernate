package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Zaur", "Tregulov", (byte) 33);
        userService.saveUser("Ivan", "Petrov", (byte) 18);
        userService.saveUser("Petr", "Ivanov", (byte) 60);
        userService.saveUser("Elena", "Sidorova", (byte) 25);
        userService.getAllUsers().stream().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
