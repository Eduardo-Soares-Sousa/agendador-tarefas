package com.javanauta.agendadorTarefas.business;

import com.javanauta.agendadorTarefas.business.dto.TarefaDto;
import com.javanauta.agendadorTarefas.business.mapper.TarefaConverter;
import com.javanauta.agendadorTarefas.business.mapper.TarefaUpdateConverter;
import com.javanauta.agendadorTarefas.infrastructure.entity.Tarefa;
import com.javanauta.agendadorTarefas.infrastructure.enums.Status;
import com.javanauta.agendadorTarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.agendadorTarefas.infrastructure.repository.TarefaRepository;
import com.javanauta.agendadorTarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final TarefaUpdateConverter tarefaUpdateConverter;
    private final JwtUtil jwtUtil;

    public TarefaDto salvarTarefa(String token, TarefaDto tarefaDto) {
        String email = jwtUtil.extractUsername(token.substring(7));

        tarefaDto.setDataCriacao(LocalDateTime.now());
        tarefaDto.setEmailUsuario(email);
        tarefaDto.setStatus(Status.PENDENTE);

        return tarefaConverter.paraTarefaDto(
                tarefaRepository.save(
                        tarefaConverter.paraTarefaEntity(tarefaDto)
                )
        );
    }

    public List<TarefaDto> buscarTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaConverter.paraListaTarefaDto(
                tarefaRepository.findByDataEventoBetweenAndStatus(dataInicial, dataFinal, Status.PENDENTE)
        );
    }

    public List<TarefaDto> buscarTarefasPorEmailUsuario(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));

        return tarefaConverter.paraListaTarefaDto(
                tarefaRepository.findByEmailUsuario(email)
        );
    }

    public void deletarTarefaPorId(String id) {
        tarefaRepository.deleteById(id);
    }

    public TarefaDto atualizaStatus(Status status, String id) {
        try {
            Tarefa tarefa = tarefaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Tarefa não encontrada. O ID " + id + " não foi localizado"
                    ));

            tarefa.setStatus(status);

            return tarefaConverter.paraTarefaDto(tarefaRepository.save(tarefa));
        }catch(ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }

    public TarefaDto atualizarDadosTarefa(TarefaDto tarefaDto, String id) {
        try {
            Tarefa tarefa = tarefaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Tarefa não encontrada. O ID " + id + " não foi localizado"
                    ));

            tarefaUpdateConverter.updateTarefas(tarefaDto, tarefa);
            return tarefaConverter.paraTarefaDto(tarefaRepository.save(tarefa));
        }catch(ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }
}
