package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.acme.entity.StudentEntity;
import org.acme.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped //Contexto das classes gerenciadas pelo Quarkus
public class StudentService {

    @Inject
    private EntityManager entityManager;
    @Inject
    StudentRepository studentRepository;
    //Injeta o repository dentro do service. Para que em to do o escopo da classe possamos acessar os métodos que tem no repository


    public List<StudentEntity> findAllStudents() {
        return studentRepository.findAll().list();
        //Sem o list() ele dá erro pois o retorno seria um panacheQuery.
    }

    public void addStudent(StudentEntity student) {
        student.setStudent_created_at(LocalDateTime.now());
        studentRepository.persist(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public void updateStudent(StudentEntity studentEntity) {
        studentRepository.persist(studentEntity);

    }

    public StudentEntity getStudentById(@Valid Long id) {
        return entityManager.find(StudentEntity.class, id);
    }

}
