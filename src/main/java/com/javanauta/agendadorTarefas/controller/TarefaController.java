package com.javanauta.agendadorTarefas.controller;

import com.javanauta.agendadorTarefas.business.TarefaService;
import com.javanauta.agendadorTarefas.business.dto.TarefaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDto>> buscarTarefaPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {

        return ResponseEntity.ok(tarefaService.buscarTarefasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDto>> buscarTarefasPorEmailUsuario(
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.buscarTarefasPorEmailUsuario(token));
    }
}
