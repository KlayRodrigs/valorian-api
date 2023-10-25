package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.acme.entity.TeacherEntity;
import org.acme.repository.TeacherRepository;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped //Contexto das classes gerenciadas pelo Quarkus
public class TeacherService {

    @Inject
    private EntityManager entityManager;
    @Inject
    TeacherRepository teacherRepository;
    //Injeta o repository dentro do service. Para que em to do o escopo da classe possamos acessar os métodos que tem no repository


    public List<TeacherEntity> findAllTeacher() {
        return teacherRepository.findAll().list();
        //Sem o list() ele dá erro pois o retorno seria um panacheQuery.
    }

    public void addTeacher(TeacherEntity teacher) {
        teacherRepository.persist(teacher); //persist salva o obj dentro do banco de dados.
        teacher.setCreated_at(LocalDateTime.now());

    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public void updateTeacher(TeacherEntity teacherEntity) {
        teacherRepository.persist(teacherEntity);
    }

    //Busco na classe Entity o parâmetro da frente, "id".
    public TeacherEntity getTeacherById(Long id) {
        return entityManager.find(TeacherEntity.class, id);
    }
}
