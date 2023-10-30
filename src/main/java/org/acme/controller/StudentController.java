package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.StudentDTO;
import org.acme.entity.StudentEntity;
import org.acme.service.StudentService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Path("/api/teacher")
public class StudentController {

    @Inject
    StudentService studentService;

    @Path("/list")
    @GET//buscar info
    public List<StudentEntity> retrieveStudent() {
        List<StudentEntity> students = new ArrayList<>();
        try {
            students = studentService.findAllStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    //ADD NULO
    @Path("/add")
    @POST //mandar info
    @Transactional //Permite que façamos alterações no banco de dados
    public void addStudent(@NotNull @Valid StudentEntity student) {
        studentService.addStudent(student);
    }

    @Path("/delete/{id}")
    @DELETE
    @Transactional
    public void deleteStudent(@NotNull @PathParam("id") Long id) {
        StudentEntity studentEntity = studentService.getStudentById(id);
        if (studentEntity == null) {
            throw new NotFoundException("O id " + id + " não está registrado");
        }
        studentService.deleteStudent(studentEntity.getId());

    }


    //UPDATE EM UM QUE NÃO EXISTE DÁ 500
    @Path("/update/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void updateStudent(@NotNull @PathParam("id") Long id, @NotNull StudentDTO studentDTO) {
        StudentEntity studentEntity = studentService.getStudentById(id);
        if (studentEntity == null) {
            throw new NotFoundException("O id " + id + " não está registrado");
        }
        if (studentDTO.getStudent_name().length() < 4) {
            throw new BadRequestException("O nome deve conter pelo menos 4 caracteres");
        }
        studentEntity.setStudent_name(studentDTO.getStudent_name());
        //NÃO CONSIGO EDITAR SOMENTE UM, TENHO QUE EDITAR TODOS
        studentEntity.setStudent_born_date(studentDTO.getStudent_born_date());
        studentEntity.setStudent_gender(studentDTO.getStudent_gender());
        studentEntity.setStudent_updated_at(LocalDateTime.now());

        studentService.updateStudent(studentEntity);
    }
}
