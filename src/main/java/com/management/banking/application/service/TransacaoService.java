package com.management.banking.application.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.management.banking.application.exception.SaldoInsuficienteException;
import com.management.banking.domain.enums.FormaPagamentoEnum;
import com.management.banking.domain.model.Conta;
import com.management.banking.domain.model.Transacao;
import com.management.banking.domain.repository.TransacaoRepository;
import com.management.banking.presentation.dto.input.TransacaoInputDTO;

import jakarta.transaction.Transactional;

@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final ContaService contaService;

    public TransacaoService(TransacaoRepository transacaoRepository, ContaService contaService) {
        this.transacaoRepository = transacaoRepository;
        this.contaService = contaService;
    }

    @Transactional
    public Transacao realizarTransacao(TransacaoInputDTO dto) {
        Conta conta = contaService.buscarPorNumero(dto.getNumeroConta());

        BigDecimal valorComTaxa = calcularValorComTaxa(dto.getValor(), dto.getFormaPagamentoEnum());

        if (conta.getSaldo().compareTo(valorComTaxa) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para a transação.");
        }

        conta.setSaldo(conta.getSaldo().subtract(valorComTaxa));
        contaService.atualizarConta(conta);

        Transacao transacao = dto.toEntity(conta.getId());
        return transacaoRepository.save(transacao);
    }

    private BigDecimal calcularValorComTaxa(BigDecimal valor, FormaPagamentoEnum forma) {
        switch (forma) {
            case D:
                return valor.multiply(BigDecimal.valueOf(1.03));
            case C:
                return valor.multiply(BigDecimal.valueOf(1.05));
            case P:
            default:
                return valor;
        }
    }
}
