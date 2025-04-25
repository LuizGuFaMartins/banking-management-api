package com.management.banking.domain.strategy;

import java.math.BigDecimal;

import com.management.banking.domain.enums.FormaPagamentoEnum;

public interface FormaPagamentoStrategy {
    BigDecimal taxarValor(BigDecimal valor);

    FormaPagamentoEnum getFormaPagamento();
}
