package repositories;

import model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    private final UserRepository userRepository = new UserRepository();

    @Test
    @DisplayName("Проверка получения пустого списка")
    void getEmptyListOfUsers() {
        assertNull(userRepository.getAllUsers());
    }

    @Test
    @DisplayName("Проверка получения заполненного списка")
    void getAllUsersTest() {
        User user = new User("login", "password");
        User user1 = new User("login1", "password1");
        User user2 = new User("login2", "password2");
        userRepository.addUser(user);
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        assertEquals(List.of(user, user1, user2), userRepository.getAllUsers());
    }

    @Test
    @DisplayName("Проверка получения существующего пользователя")
    void getExistUserByLoginTest() {
        User user = new User("login", "password");
        User user1 = new User("login1", "password1");
        User user2 = new User("login2", "password2");
        userRepository.addUser(user);
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        assertEquals(user, userRepository.getUserByLogin("login").orElse(null));
    }

    @Test
    @DisplayName("Проверка получения не существующего пользователя")
    void getNotExistUserByLoginTest() {
        User user = new User("login", "password");
        User user1 = new User("login1", "password1");
        User user2 = new User("login2", "password2");
        userRepository.addUser(user);
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        assertNotEquals(user, userRepository.getUserByLogin("login3").orElse(null));
    }

    @Test
    @DisplayName("Проверка получения существующего пользователя по логину и паролю")
    void getUserByLoginAndPassTest() {
        User user = new User("login", "password");
        User user1 = new User("login1", "password1");
        User user2 = new User("login2", "password2");
        userRepository.addUser(user);
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        assertEquals(user, userRepository.getUserByLoginAndPass("login", "password").orElse(null));
    }

    @Test
    @DisplayName("Проверка получения существующего пользователя по логину")
    void getUserByOneLoginTest() {
        User user = new User("login", "password");
        User user1 = new User("login1", "password1");
        User user2 = new User("login2", "password2");
        userRepository.addUser(user);
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        assertEquals(user, userRepository.getAllUsers().stream()
                .filter(u -> u.getLogin().equals(user.getLogin()))
                .findAny().orElse(null));
    }

    @Test
    @DisplayName("Проверка получения существующего пользователя по паролю")
    void getUserByOnePassTest() {
        User user = new User("login", "password");
        User user1 = new User("login1", "password1");
        User user2 = new User("login2", "password2");
        userRepository.addUser(user);
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        assertEquals(user, userRepository.getAllUsers().stream()
                .filter(u -> u.getPassword().equals(user.getPassword()))
                .findAny().orElse(null));
    }
}