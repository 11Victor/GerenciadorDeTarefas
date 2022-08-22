package br.com.manager.tasks.dto;

import br.com.manager.tasks.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPickerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    public UserPickerDTO(User obj) {
        this.id = obj.getId();
        this.name = obj.getName();
    }
}
