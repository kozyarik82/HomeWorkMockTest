package me.kozyar.mock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.NoInteractions;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void whenUsersInRepositoryThenShouldReturnLogins() {
        when(userRepository.getAllUsers())
                .thenReturn(List.of(new User("Login1", "Password1"), new User("Login2", "Password2")));
        Assertions.assertThat(userService.getAllLogins().contains(userRepository));
    }

    @Test
    void whenRepositoryIsEmptyThenLoginsShouldBeMissing() {
        when(userRepository.getAllUsers()).thenReturn(List.of());
        Assertions.assertThat(userService.getAllLogins().isEmpty());
    }

    @Test
    void whenCorrectUserIsAddedThenUserIsCalledFromRepository() throws UserNonUniqueException {
        when(userRepository.getAllUsers()).thenReturn(List.of());
        when(userRepository.addUser(any(User.class))).thenReturn(null);
        userService.addUser("Login1","Password1");
        verify(userRepository).addUser(any());
    }

    @Test
    void whenInvalidUserIsPassedThenServiceThrowsException() {
        Assertions.assertThatThrownBy(() -> userService.addUser("", ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("User login and password should be defined");
        verify(userRepository, new NoInteractions()).getAllUsers();
    }

    @Test
    void whenExistingUserIsPassedThenServiceThrowsException() {
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("Login1", "Password1")));
        Assertions.assertThatThrownBy(() -> userService.addUser("Login1", "Password1"))
                .isInstanceOf(UserNonUniqueException.class)
                .hasMessage("User already exist");
    }

    @Test
    void whenSearchingUserByLoginThenServiceShouldReturnTrue() {
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("Login1","Password1")));
        Assertions.assertThat(userService.isUserOfLogin("Login1", "Password1"));
    }
}
