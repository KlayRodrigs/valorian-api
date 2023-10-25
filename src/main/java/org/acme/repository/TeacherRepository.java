package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.TeacherEntity;

@ApplicationScoped //Diz para o quarkus que essa classe, essa classe quando a app for inicializado é pra reconhecr que essa classe que ele precisa gerenciar. Se não por isso estariamos isolando essa classe do controle de contexto do Quarkus
public class TeacherRepository implements PanacheRepository<TeacherEntity> {
//Usar o padrão repository para acessar o banco de dados. A classe "TeacherRepository" agora pode implementar os métodos que tão dentro do panache. São eles os que inserem e buscam do BD. Ela vai trabalhar com a classe passada no parâmetro

}
