package com.javanauta.agendadorTarefas.business;

import com.javanauta.agendadorTarefas.business.dto.TarefaDto;
import com.javanauta.agendadorTarefas.business.mapper.TarefaConverter;
import com.javanauta.agendadorTarefas.infrastructure.enums.Status;
import com.javanauta.agendadorTarefas.infrastructure.repository.TarefaRepository;
import com.javanauta.agendadorTarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
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


}
