package com.Bookery.TestTask.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegistrationDto {
    long id;
    @NotEmpty
    String username;
    @NotEmpty
    String email;
    @NotEmpty
    String password;
}
