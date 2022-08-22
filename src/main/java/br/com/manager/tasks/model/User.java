package br.com.manager.tasks.model;

import br.com.manager.tasks.model.embeddable.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString
@Entity
@Table(name = "tb_user")
public class User extends Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @EqualsAndHashCode.Include
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String role = "user";

    @Column(nullable = false)
    private boolean enable = true;

    @ManyToOne(optional = true)
    private User manager;

}
