package org.acme.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDTO {
    @NotNull(message = "Preencha corretamente")
    @NotEmpty(message = "Preencha corretamente")
    private String name;

}
