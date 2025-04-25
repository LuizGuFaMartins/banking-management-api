package com.management.banking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.banking.application.service.TransacaoService;
import com.management.banking.domain.enums.FormaPagamentoEnum;
import com.management.banking.domain.model.Conta;
import com.management.banking.domain.model.Transacao;
import com.management.banking.presentation.controller.TransacaoController;
import com.management.banking.presentation.dto.input.TransacaoInputDTO;

@WebMvcTest(TransacaoController.class)
public class TransacaoControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private TransacaoService transacaoService;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        public void deveRealizarTransacaoComSucesso() throws Exception {
                UUID contaId = UUID.randomUUID();

                Conta conta = Conta.builder()
                                .id(contaId)
                                .numeroConta(234)
                                .saldo(new BigDecimal("500"))
                                .build();

                TransacaoInputDTO transacaoInputDTO = TransacaoInputDTO.builder()
                                .numeroConta(234)
                                .formaPagamento("D")
                                .valor(new BigDecimal("100"))
                                .build();

                BigDecimal valorComTaxa = new BigDecimal("103");

                BigDecimal saldoFinal = conta.getSaldo().subtract(valorComTaxa);

                Transacao transacao = Transacao.builder()
                                .contaId(contaId)
                                .valor(valorComTaxa)
                                .formaPagamento(FormaPagamentoEnum.DEBITO)
                                .build();

                conta.setSaldo(saldoFinal);

                when(transacaoService.realizarTransacao(any(TransacaoInputDTO.class))).thenReturn(transacao);

                mockMvc.perform(post("/api/transacao")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(transacaoInputDTO)))
                                .andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.valor").value(valorComTaxa));

                assertEquals(new BigDecimal("103"), valorComTaxa);
                assertEquals(new BigDecimal("397"), saldoFinal);
        }

        @Test
        public void deveRetornar400QuandoDadosInvalidos() throws Exception {
                TransacaoInputDTO transacaoInputDTO = TransacaoInputDTO.builder()
                                .numeroConta(null)
                                .formaPagamento("")
                                .valor(new BigDecimal("-100"))
                                .build();

                mockMvc.perform(post("/api/transacao")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(transacaoInputDTO)))
                                .andExpect(status().isBadRequest());
        }
}
