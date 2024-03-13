package ru.khakimov.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.khakimov.audit.annotation.Audit;
import ru.khakimov.dto.UserCredentials;
import ru.khakimov.service.UserService;

@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;

    @PostMapping("api/register")
    @Audit
    public String registerUser(@RequestBody @Valid UserCredentials userCredentials) {
        userService.isLoginVacantOrElseThrow(userCredentials.getLogin());
        userService.registerUser(userCredentials);
        return "Success registration";
    }
}
