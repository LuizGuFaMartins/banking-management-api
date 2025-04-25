package com.management.banking.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.banking.application.service.ContaService;
import com.management.banking.domain.model.Conta;
import com.management.banking.presentation.dto.input.ContaInputDTO;
import com.management.banking.presentation.dto.output.ContaOutputDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public ResponseEntity<ContaOutputDTO> buscarConta(@RequestParam Integer numeroConta) {
        Conta conta = contaService.buscarPorNumero(numeroConta);
        return ResponseEntity.ok(ContaOutputDTO.fromEntity(conta));
    }

    @PostMapping
    public ResponseEntity<ContaOutputDTO> criarConta(@RequestBody @Valid ContaInputDTO dto) {
        Conta conta = contaService.criarConta(dto.toEntity());
        return ResponseEntity.status(201).body(ContaOutputDTO.fromEntity(conta));
    }

}
