package org.acme.controller;

import jakarta.enterprise.inject.build.compatible.spi.Validation;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.TeacherDTO;
import org.acme.entity.TeacherEntity;
import org.acme.service.TeacherService;

import java.time.LocalDate;
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

    @Path("/add")
    @POST //mandar info
    @Transactional //Permite que façamos alterações no banco de dados
    public void addTeacher(@Valid TeacherEntity teacher) {
        teacherService.addTeacher(teacher);
    }

    @Path("/delete/{id}")
    @DELETE
    @Transactional
    public void removeTeacher(@PathParam("id") Long id) {
        TeacherEntity teacherEntity = teacherService.getTeacherById(id);

        teacherService.deleteTeacher(teacherEntity.getId());

    }

    @Path("/update/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void updateTeacher(@PathParam("id") Long id, TeacherDTO teacherDTO) {
        TeacherEntity teacherEntity = teacherService.getTeacherById(id);

        teacherEntity.setName(teacherDTO.getName());
        teacherEntity.setUpdated_at(LocalDateTime.now());

        teacherService.updateTeacher(teacherEntity);
    }
}
