package pdev.com.agenda.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Table(name = "usuario")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String usuario;

    @NotBlank
    private String senha;

    @Column(name = "token")
    private String token;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @Column(name = "is_first_login")
    private boolean isFirstLogin;

}
