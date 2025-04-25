package com.management.banking.presentation.dto.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.management.banking.domain.model.Transacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoOutputDTO {
    private UUID id;
    private UUID contaId;
    private BigDecimal valor;
    private String formaPagamento;
    private LocalDateTime dataCriacao;

    public static TransacaoOutputDTO fromEntity(Transacao transacao) {
        return TransacaoOutputDTO.builder()
                .id(transacao.getId())
                .contaId(transacao.getContaId())
                .valor(transacao.getValor())
                .formaPagamento(transacao.getFormaPagamento().name())
                .dataCriacao(transacao.getDataCriacao())
                .build();
    }
}