package com.management.banking.presentation.dto.output;

import java.math.BigDecimal;

import com.management.banking.domain.model.Conta;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContaOutputDTO {
    private Integer numeroConta;
    private BigDecimal saldo;

    public static ContaOutputDTO fromEntity(Conta conta) {
        return ContaOutputDTO.builder()
                .numeroConta(conta.getNumeroConta())
                .saldo(conta.getSaldo())
                .build();
    }
}
