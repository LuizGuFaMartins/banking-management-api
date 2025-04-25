package com.management.banking.presentation.dto.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.management.banking.domain.enums.FormaPagamentoEnum;
import com.management.banking.domain.model.Transacao;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransacaoInputDTO {

    @NotBlank(message = "A forma de pagamento é obrigatória")
    private String formaPagamento;

    @NotNull(message = "O número da conta é obrigatório")
    private Integer numeroConta;

    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value = "0.01", inclusive = true, message = "O valor da transação deve ser maior que zero")
    private BigDecimal valor;

    public FormaPagamentoEnum getFormaPagamentoEnum() {
        return FormaPagamentoEnum.valueOf(this.formaPagamento);
    }

    public Transacao toEntity(UUID contaId) {
        return Transacao.builder()
                .contaId(contaId)
                .formaPagamento(this.getFormaPagamentoEnum())
                .valor(this.valor)
                .data(LocalDateTime.now())
                .build();
    }
}
