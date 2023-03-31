package services;

import exceptions.UserNonUniqueException;
import model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.UserRepository;
import services.impl.UserServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    @DisplayName("Проверка получения списка логинов")
    void getAllLoginsTest() {
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("login", "password")));
        assertThat(userService.getAllLogins()).isEqualTo(List.of("login"));
    }

    @Test
    @DisplayName("Проверка исключения создания невалидного пользователя")
    void createEmptyUserExceptionTest() {
        assertThatThrownBy(() -> userService.createUser(" ", null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка исключения создания существующего пользователя")
    void createExistsUserExceptionTest() {
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("login", "password")));
        assertThatThrownBy(() -> userService.createUser("login", "password"))
                .isInstanceOf(UserNonUniqueException.class);
    }

    @Test
    @DisplayName("Проверка верификации существующего пользователя")
    void verificationTrueUserTest() {
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("login", "password")));
        assertThat(userService.verificationUser("login", "password"))
                .isTrue();
    }

    @Test
    @DisplayName("Проверка верификации несуществующего пользователя")
    void verificationFalseUserTest() {
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("login", "password")));
        assertThat(userService.verificationUser("login1", "password1"))
                .isFalse();
    }
}