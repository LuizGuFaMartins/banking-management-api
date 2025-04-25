package com.management.banking.domain.strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.management.banking.domain.enums.FormaPagamentoEnum;

@Component
public class CreditoStrategy implements FormaPagamentoStrategy {
    @Override
    public BigDecimal taxarValor(BigDecimal valor) {
        return valor.multiply(new BigDecimal("1.05"));
    }

    @Override
    public FormaPagamentoEnum getFormaPagamento() {
        return FormaPagamentoEnum.CREDITO;
    }
}