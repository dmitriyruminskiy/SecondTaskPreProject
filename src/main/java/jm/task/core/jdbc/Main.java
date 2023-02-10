package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        User user1 = new User("Ivan", "Ivanov", (byte) 27);
        User user2 = new User("Petr", "Petrov", (byte) 18);
        User user3 = new User("Magomed", "Magomedov", (byte) 30);
        User user4 = new User("Nursultan", "Nazarbaev", (byte) 55);

        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных \n", user1.getName());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных \n", user2.getName());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных \n", user3.getName());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных \n\n", user4.getName());

        for (User allUser : userService.getAllUsers()) {
            System.out.println(allUser);
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();


    }
}
