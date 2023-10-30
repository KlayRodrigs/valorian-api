package org.acme.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity //Diz para o Hibernate e JPA para entender que essa classe faz uma ligação com o BD, ou seja representa no bd.
@Data //Simplifica a criação de métodos para a classe entre outros, hashes, ToStrings, etc
@NoArgsConstructor // Esses dois dispensam a necessidade de um construtor(@Data e @NAC);
public class StudentEntity {
    @Column(name = "student_id")
    @Id //Diz para o banco dados que toda vez que essa info ela for criada ela vai ser uma primaryKey
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name")
    @Size(min = 5)
    @NotNull
    private String student_name;

    @Column(name = "student_born_date")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate student_born_date;

    @Column(name = "student_gender")
    @NotNull
    private String student_gender;

    @Column(name = "student_created_at")
    private LocalDateTime student_created_at;

    @Column(name = "student_updated_at")
    private LocalDateTime student_updated_at;
}
