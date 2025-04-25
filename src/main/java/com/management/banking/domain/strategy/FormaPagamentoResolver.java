package com.management.banking.domain.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.management.banking.domain.enums.FormaPagamentoEnum;

@Component
public class FormaPagamentoResolver {

    private final Map<FormaPagamentoEnum, FormaPagamentoStrategy> strategies = new HashMap<>();

    public FormaPagamentoResolver(List<FormaPagamentoStrategy> list) {
        list.forEach(s -> strategies.put(s.getFormaPagamento(), s));
    }

    public FormaPagamentoStrategy resolver(FormaPagamentoEnum formaPagamento) {
        return Optional.ofNullable(strategies.get(formaPagamento))
                .orElseThrow(() -> new IllegalArgumentException("Forma de pagamento não suportada: " + formaPagamento));
    }
}