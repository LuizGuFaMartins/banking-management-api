package com.management.banking.application.service;

import org.springframework.stereotype.Service;

import com.management.banking.domain.repository.TransacaoRepository;

@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }
}
