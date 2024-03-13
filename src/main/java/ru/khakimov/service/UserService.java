package ru.khakimov.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.khakimov.dto.UserCredentials;
import ru.khakimov.exception.UserAlreadyExistsException;
import ru.khakimov.model.User;
import ru.khakimov.repository.UserRepository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(UserCredentials userCredentials) {
        User user = new User();
        user.setLogin(userCredentials.getLogin());
        user.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
        user.setRoles(userCredentials.getRoles());
        user.setCreationTime(OffsetDateTime.now());
        userRepository.save(user);

    }

    @Cacheable("users")
    public void isLoginVacantOrElseThrow(String login) {
        Optional<User> byLogin = userRepository.findByLogin(login);
        if (byLogin.isPresent()) {
            throw new UserAlreadyExistsException(String.format("user with login: '%s', already exists", login));
        }
    }

    public String getUserRolesByLogin(String login) {
        return userRepository.findByLogin(login)
                .map(User::getRoles)
                .orElseGet(() -> "");

    }
}
