package ru.khakimov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.khakimov.configuration.UserDetailsImpl;
import ru.khakimov.model.User;
import ru.khakimov.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(login);
        return user.map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("login: " + login + " not found"));
    }
}
