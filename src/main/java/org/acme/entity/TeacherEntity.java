package org.acme.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity //Diz para o Hibernate e JPA para entender que essa classe faz uma ligação com o BD, ou seja representa no bd.
@Data //Simplifica a criação de métodos para a classe entre outros
@NoArgsConstructor // Esses dois dispensam a necessidade de um construtor(@Data e @NAC);
public class TeacherEntity {
    @Column(name = "teacher_id")
    @Id //Diz para o banco dados que toda vez que essa info ela for criada ela vai ser uma primaryKey
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_name")
    @Size(min = 4)
    private String name;

    @Column(name = "teacher_created_at")
    private LocalDateTime created_at;

    @Column(name = "teacher_updated_at")
    private LocalDateTime updated_at;

}
