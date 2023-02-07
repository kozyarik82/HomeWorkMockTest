package me.kozyar.mock;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(String userLogin, String userPassword) throws UserNonUniqueException {
        User user = new User(userLogin, userPassword);
        if (userLogin == null || userLogin.isBlank() || userPassword == null || userPassword.isBlank()) {
            throw new IllegalArgumentException("User login and password should be defined");
        }
        boolean userExist = this.userRepository
                .getAllUsers()
                .stream()
                .anyMatch(u -> u.equals(user));
        if (userExist) {
            throw new UserNonUniqueException("User already exist");
        }
        this.userRepository.addUser(user);
    }

    public List<String> getAllLogins() {
        return this.userRepository
                .getAllUsers()
                .stream()
                .map(u -> u.getLogin())
                .collect(Collectors.toList());
    }

//    public boolean isUserOfLogin(String userLogin, String userPassword) {
//        userRepository.getUserByLoginAndPassword(userLogin, userPassword)
//                .stream()
//                .anyMatch(u -> u.getLogin().equals(userLogin) && u.getPassword().equals(userPassword));
//        return true;
//    }

    public boolean isUserOfLogin(String userLogin, String userPassword) {
        return userRepository.getAllUsers()
                .stream()
                .anyMatch(u -> u.getLogin().equals(userLogin) && u.getPassword().equals(userPassword));
    }
}







