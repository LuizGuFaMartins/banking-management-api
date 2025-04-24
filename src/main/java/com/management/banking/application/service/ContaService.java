package com.management.banking.application.service;

import org.springframework.stereotype.Service;

import com.management.banking.domain.repository.ContaRepository;

@Service
public class ContaService {
    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }
}
