package com.management.banking.domain.strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.management.banking.domain.enums.FormaPagamentoEnum;

@Component
public class PixStrategy implements FormaPagamentoStrategy {
    @Override
    public BigDecimal taxarValor(BigDecimal valor) {
        return valor;
    }

    @Override
    public FormaPagamentoEnum getFormaPagamento() {
        return FormaPagamentoEnum.PIX;
    }
}
