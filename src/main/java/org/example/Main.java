package org.example;


import me.kozyar.mock.User;
import me.kozyar.mock.UserRepository;
import me.kozyar.mock.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Login1", "Password1");
        User user2 = new User("Login2", "Password2");
        User user3 = new User("Login3", "Password3");
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);
        System.out.println(userRepository.getAllUsers());
        System.out.println(userRepository.getUserByLoginAndPassword("User1", "Login1"));
        System.out.println(userRepository.getUserByLogin("Login2"));
        userRepository.addUser(user2);
        System.out.println(userRepository.getAllUsers());
        UserService userService = new UserService(userRepository);
        System.out.println(userService.isUserOfLogin("Login1", "Password1"));

    }
}