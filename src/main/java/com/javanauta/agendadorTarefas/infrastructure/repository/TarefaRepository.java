package com.javanauta.agendadorTarefas.infrastructure.repository;

import com.javanauta.agendadorTarefas.infrastructure.entity.Tarefa;
import com.javanauta.agendadorTarefas.infrastructure.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<Tarefa, String> {
    List<Tarefa> findByDataEventoBetweenAndStatus(LocalDateTime dataInicial,
                                                  LocalDateTime dataFinal,
                                                  Status status);
    List<Tarefa> findByEmailUsuario(String email);
}
