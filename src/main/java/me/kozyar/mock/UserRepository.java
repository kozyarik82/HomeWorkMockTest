package me.kozyar.mock;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class UserRepository {
    private final List<User> users = new ArrayList<>();

    // Получение всех пользователей
    public List<User> getAllUsers() {
        return new ArrayList<User>(users);
    }


    // Поиск пользователя по логину
    public Optional<User> getUserByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    // Поиск пользователя по логину и паролю
    public Optional<User> getUserByLoginAndPassword(String login, String password) {
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    // Добавление пользователя
    public User addUser(User user) {
        users.add(user);
        return user;
    }


}
