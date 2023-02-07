package me.kozyar.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class UserRepositoryTest {

    @Test
    public void whenThereAreNoUsersThenTheUserRepositoryReturnsAnEmptyList() {
        UserRepository userRepository = new UserRepository();
        List<User> expected = userRepository.getAllUsers();
        List<User> actual = new ArrayList<>();
        Assertions.assertEquals(expected,actual);
        Assertions.assertNotNull(expected);
    }

    @Test
    public void whenThereAreNoUsersThenTheUserRepositoryReturnsAListOfUsers() {
        User user1 = new User("Login1", "Password1");
        User user2 = new User("Login2", "Password2");
        User user3 = new User("Login3", "Password3");
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);
        List<User> expected = userRepository.getAllUsers();
        List<User> actual = new ArrayList<User>();
        actual.add(user1);
        actual.add(user2);
        actual.add(user3);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void whenLookingForAnExistingUserByLoginThenUsersRepositoryShouldReturnThatUser() {
        User user1 = new User("Login1", "Password1");
        User user2 = new User("Login2", "Password2");
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        Optional<User> expected = userRepository.getUserByLogin("Login1");
        Optional<User> actual = Optional.of(user1);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void whenLookingForNotExistingUserByLoginThenUsersRepositoryShouldReturnAnEmptyList() {
        User user2 = new User("Login2", "Password2");
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(user2);
        Optional<User> expected = userRepository.getUserByLogin("Login1");
        Optional<User> actual = Optional.of(user2);
        Assertions.assertNotEquals(expected,actual);
    }

    @Test
    public void whenLookingForAnExistingUserByLoginAndPasswordThenUsersRepositoryShouldReturnThatUser() {
        User user1 = new User("Login1", "Password1");
        User user2 = new User("Login2", "Password2");
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        Optional<User> expected = userRepository.getUserByLoginAndPassword("Login1", "Password1");
        Optional<User> actual = Optional.of(user1);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void whenWeAreLookingForAUserByLoginAndPasswordButTheLoginDoesNotMatchThenTheUserRepositoryShouldReturnAnEmpty() {
        User user1 = new User("Login1", "Password1");
        User user2 = new User("Login2", "Password2");
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        Optional<User> expected = userRepository.getUserByLoginAndPassword("Login2", "Password1");
        Optional<User> actual = Optional.of(user1);
        Assertions.assertNotEquals(expected,actual);
    }
    @Test
    public void whenWeAreLookingForAUserByLoginAndPasswordButThePasswordDoesNotMatchThenTheUserRepositoryShouldReturnAnEmpty() {
        User user1 = new User("Login1", "Password1");
        User user2 = new User("Login2", "Password2");
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        Optional<User> expected = userRepository.getUserByLoginAndPassword("Login2", "Password1");
        Optional<User> actual = Optional.of(user2);
        Assertions.assertNotEquals(expected,actual);
    }
}
