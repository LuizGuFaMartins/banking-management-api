package com.management.banking.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.banking.application.service.TransacaoService;
import com.management.banking.domain.model.Transacao;
import com.management.banking.presentation.dto.input.TransacaoInputDTO;
import com.management.banking.presentation.dto.output.TransacaoOutputDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<TransacaoOutputDTO> realizarTransacao(@RequestBody @Valid TransacaoInputDTO dto) {
        Transacao transacao = transacaoService.realizarTransacao(dto);
        return ResponseEntity.status(201).body(TransacaoOutputDTO.fromEntity(transacao));
    }
}
