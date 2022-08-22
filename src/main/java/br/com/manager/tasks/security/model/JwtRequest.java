package br.com.manager.tasks.security.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
}
