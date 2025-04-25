package com.management.banking.application.service;

import org.springframework.stereotype.Service;

import com.management.banking.application.exception.ContaJaExistenteException;
import com.management.banking.application.exception.ContaNaoEncontradaException;
import com.management.banking.domain.model.Conta;
import com.management.banking.domain.repository.ContaRepository;

import jakarta.transaction.Transactional;

@Service
public class ContaService {
    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta buscarPorNumero(Integer numeroConta) {
        return contaRepository.findByNumeroConta(numeroConta)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));
    }

    @Transactional
    public Conta criarConta(Conta conta) {
        if (contaRepository.findByNumeroConta(conta.getNumeroConta()).isPresent()) {
            throw new ContaJaExistenteException("Conta já existente com o número: " + conta.getNumeroConta());
        }
        return contaRepository.save(conta);
    }

    @Transactional
    public Conta atualizarConta(Conta conta) {
        return contaRepository.save(conta);
    }

}
