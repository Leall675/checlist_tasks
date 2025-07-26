package com.checklist.tasks.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private String id;
    @NotBlank(message = "O nome de usuário é obrigatório.")
    private String username;
    @NotBlank(message = "O email do usuário é obrigatório.")
    private String email;
    @NotBlank(message = "A senha do usuário é obrigatório.")
    @Size(min = 6, message = "A senha deve conter no minimo 6 caracteres.")
    private String password;
}
