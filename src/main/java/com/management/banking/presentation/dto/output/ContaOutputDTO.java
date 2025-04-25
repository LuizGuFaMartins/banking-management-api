package com.management.banking.presentation.dto.output;

import java.math.BigDecimal;
import java.util.UUID;

import com.management.banking.domain.model.Conta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaOutputDTO {
    private UUID id;
    private Integer numeroConta;
    private BigDecimal saldo;

    public static ContaOutputDTO fromEntity(Conta conta) {
        return ContaOutputDTO.builder()
                .id(conta.getId())
                .numeroConta(conta.getNumeroConta())
                .saldo(conta.getSaldo())
                .build();
    }
}
