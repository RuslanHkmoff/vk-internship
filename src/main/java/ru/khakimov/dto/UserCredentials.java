package ru.khakimov.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCredentials {
    @NotNull
    @NotBlank
    @Size(min = 2, max = 64)
    private String login;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 60)
    private String password;
    @NotNull
    private String roles;
}
