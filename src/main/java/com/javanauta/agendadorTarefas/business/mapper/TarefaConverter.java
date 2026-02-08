package com.javanauta.agendadorTarefas.business.mapper;

import com.javanauta.agendadorTarefas.business.dto.TarefaDto;
import com.javanauta.agendadorTarefas.infrastructure.entity.Tarefa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {
    Tarefa paraTarefaEntity(TarefaDto tarefaDto);
    TarefaDto paraTarefaDto(Tarefa tarefa);
    List<Tarefa> paraListaTarefaEntity(List<TarefaDto> tarefaDtos);
    List<TarefaDto> paraListaTarefaDto(List<Tarefa> tarefas);
}
