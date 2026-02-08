package com.javanauta.agendadorTarefas.controller;

import com.javanauta.agendadorTarefas.business.TarefaService;
import com.javanauta.agendadorTarefas.business.dto.TarefaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefa")
public class TarefaController {
    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDto> salvarTarefa(@RequestHeader("Authorization") String token,
                                                  @RequestBody TarefaDto tarefaDto) {
        return ResponseEntity.ok(tarefaService.salvarTarefa(token, tarefaDto));
    }
}
