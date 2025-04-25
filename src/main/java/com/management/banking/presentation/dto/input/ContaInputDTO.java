package com.management.banking.presentation.dto.input;

import java.math.BigDecimal;

import com.management.banking.domain.model.Conta;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContaInputDTO {
    @NotNull(message = "O número da conta é obrigatório")
    private Integer numeroConta;

    @NotNull(message = "O saldo é obrigatório")
    @DecimalMin(value = "0.0", inclusive = true, message = "O saldo inicial não pode ser negativo")
    private BigDecimal saldo;

    public Conta toEntity() {
        return Conta.builder()
                .numeroConta(this.numeroConta)
                .saldo(this.saldo)
                .build();
    }
}
