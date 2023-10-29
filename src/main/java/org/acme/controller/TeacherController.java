package org.acme.controller;

import jakarta.enterprise.inject.build.compatible.spi.Validation;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.TeacherDTO;
import org.acme.entity.TeacherEntity;
import org.acme.service.TeacherService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Path("/api/teacher")
public class TeacherController {

    @Inject
    TeacherService teacherService;

    @Path("/list")
    @GET//buscar info
    public List<TeacherEntity> retrieveTeacher() {
        List<TeacherEntity> teachers = new ArrayList<>();
        try {
            teachers = teacherService.findAllTeacher();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teachers;
    }

    //ADD NULO
    @Path("/add")
    @POST //mandar info
    @Transactional //Permite que façamos alterações no banco de dados
    public void addTeacher(@NotNull @Valid TeacherEntity teacher) {
        teacherService.addTeacher(teacher);
    }

    @Path("/delete/{id}")
    @DELETE
    @Transactional
    public void deleteTeacher(@NotNull @PathParam("id") Long id) {
        TeacherEntity teacherEntity = teacherService.getTeacherById(id);
        if (teacherEntity == null) {
            throw new NotFoundException("O id " + id + " não está registrado");
        }
        teacherService.deleteTeacher(teacherEntity.getId());

    }


    //UPDATE EM UM QUE NÃO EXISTE DÁ 500
    @Path("/update/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void updateTeacher(@NotNull @PathParam("id") Long id, @NotNull TeacherDTO teacherDTO) {
        TeacherEntity teacherEntity = teacherService.getTeacherById(id);
        if (teacherEntity == null) {
            throw new NotFoundException("O id " + id + " não está registrado");
        }
        if (teacherDTO.getTeacher_name().length() < 4) {
            throw new BadRequestException("O nome deve conter pelo menos 4 caracteres");
        }
        teacherEntity.setTeacher_name(teacherDTO.getTeacher_name());
        //NÃO CONSIGO EDITAR SOMENTE UM, TENHO QUE EDITAR TODOS
        teacherEntity.setTeacher_born_date(teacherDTO.getTeacher_born_date());
        teacherEntity.setTeacher_gender(teacherDTO.getTeacher_gender());
        teacherEntity.setTeacher_updated_at(LocalDateTime.now());

        teacherService.updateTeacher(teacherEntity);
    }
}
