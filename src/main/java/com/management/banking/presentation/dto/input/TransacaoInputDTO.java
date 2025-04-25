package com.management.banking.presentation.dto.input;

import java.math.BigDecimal;

import com.management.banking.domain.enums.FormaPagamentoEnum;
import com.management.banking.domain.model.Transacao;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoInputDTO {

    @NotBlank(message = "A forma de pagamento é obrigatória")
    private String formaPagamento;

    @NotNull(message = "O número da conta é obrigatório")
    private Integer numeroConta;

    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value = "0.01", inclusive = true, message = "O valor da transação deve ser maior que zero")
    private BigDecimal valor;

    public Transacao toEntity() {
        return Transacao.builder()
                .formaPagamento(FormaPagamentoEnum.fromCodigo(this.formaPagamento))
                .valor(this.valor)
                .build();
    }
}
