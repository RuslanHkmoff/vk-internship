package ru.khakimov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.khakimov.model.User;
import ru.khakimov.repository.UserRepository;
import ru.khakimov.service.UserService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = App.class)
public class UserServiceTest {
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @BeforeEach
    void init() {
        User testUser = new User();
        testUser.setLogin("test");
        testUser.setPassword("test");
        testUser.setCreationTime(OffsetDateTime.now());
        testUser.setRoles("ROLES_ADMIN");
        when(userRepository.save(testUser)).thenReturn(testUser);
        when(userRepository.findByLogin("test")).thenReturn(Optional.of(testUser));
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsLoginVacantDoesntThrow() {
        assertDoesNotThrow(() -> userService.isLoginVacantOrElseThrow("test"));
    }

    @Test
    void testIsLoginVacantThrow() {
        assertThrows(
                NoSuchElementException.class,
                () -> userService.isLoginVacantOrElseThrow("unknown")
        );
    }
}
