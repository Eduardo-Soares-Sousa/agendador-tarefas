package com.javanauta.agendadorTarefas.infrastructure.repository;

import com.javanauta.agendadorTarefas.infrastructure.entity.Tarefa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<Tarefa, String> {
    List<Tarefa> findByDataEventoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<Tarefa> findByEmailUsuario(String email);
}
