package com.management.banking.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FormaPagamentoEnum {

    PIX("P"),
    CREDITO("C"),
    DEBITO("D");

    private final String codigo;

    FormaPagamentoEnum(String codigo) {
        this.codigo = codigo;
    }

    @JsonValue
    public String getCodigo() {
        return codigo;
    }

    @JsonCreator
    public static FormaPagamentoEnum fromCodigo(String codigo) {
        for (FormaPagamentoEnum forma : values()) {
            if (forma.getCodigo().equalsIgnoreCase(codigo)) {
                return forma;
            }
        }
        throw new IllegalArgumentException("Forma de pagamento inválida: " + codigo);
    }
}
