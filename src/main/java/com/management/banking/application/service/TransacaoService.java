package com.management.banking.application.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.management.banking.application.exception.SaldoInsuficienteException;
import com.management.banking.domain.enums.FormaPagamentoEnum;
import com.management.banking.domain.model.Conta;
import com.management.banking.domain.model.Transacao;
import com.management.banking.domain.repository.TransacaoRepository;
import com.management.banking.domain.strategy.FormaPagamentoResolver;
import com.management.banking.domain.strategy.FormaPagamentoStrategy;
import com.management.banking.presentation.dto.input.TransacaoInputDTO;

import jakarta.transaction.Transactional;

@Service
public class TransacaoService {
    private final FormaPagamentoResolver formaPagamentoResolver;
    private final TransacaoRepository transacaoRepository;
    private final ContaService contaService;

    public TransacaoService(FormaPagamentoResolver formaPagamentoResolver, TransacaoRepository transacaoRepository,
            ContaService contaService) {
        this.formaPagamentoResolver = formaPagamentoResolver;
        this.transacaoRepository = transacaoRepository;
        this.contaService = contaService;
    }

    @Transactional
    public Transacao realizarTransacao(TransacaoInputDTO dto) {
        Conta conta = contaService.buscarPorNumero(dto.getNumeroConta());

        FormaPagamentoStrategy strategy = formaPagamentoResolver
                .resolver(FormaPagamentoEnum.fromCodigo(dto.getFormaPagamento()));
        BigDecimal valorComTaxa = strategy.taxarValor(dto.getValor());

        if (conta.getSaldo().compareTo(valorComTaxa) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para a transação.");
        }

        conta.setSaldo(conta.getSaldo().subtract(valorComTaxa));
        contaService.atualizarConta(conta);

        Transacao transacao = dto.toEntity();
        transacao.setContaId(conta.getId());
        transacao.setValor(valorComTaxa);

        return transacaoRepository.save(transacao);
    }

}
