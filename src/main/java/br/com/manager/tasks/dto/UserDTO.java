package br.com.manager.tasks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "Nome do usuário não informado")
    private String name;

    private String password;

    @Email
    @NotNull(message = "E-mail do usuário não informado")
    private String email;

    private String role;
    
    private UserDTO manager;
    
}
