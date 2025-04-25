package com.management.banking.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.management.banking.application.exception.ContaNaoEncontradaException;
import com.management.banking.application.service.ContaService;
import com.management.banking.domain.model.Conta;
import com.management.banking.presentation.controller.ContaController;
import com.management.banking.presentation.dto.input.ContaInputDTO;

@WebMvcTest(ContaController.class)
class ContaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContaService contaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarContaComSucesso() throws Exception {
        ContaInputDTO inputDTO = ContaInputDTO.builder()
                .numeroConta(1001)
                .saldo(new BigDecimal("500.00"))
                .build();

        Conta contaCriada = Conta.builder()
                .id(UUID.randomUUID())
                .numeroConta(1001)
                .saldo(new BigDecimal("500.00"))
                .build();

        when(contaService.criarConta(any(Conta.class))).thenReturn(contaCriada);

        mockMvc.perform(post("/api/conta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numeroConta").value(1001))
                .andExpect(jsonPath("$.saldo").value(500.00));
    }

    @Test
    void deveRetornarContaPorNumero() throws Exception {
        Conta conta = Conta.builder()
                .id(UUID.randomUUID())
                .numeroConta(2002)
                .saldo(new BigDecimal("1000.00"))
                .build();

        when(contaService.buscarPorNumero(2002)).thenReturn(conta);

        mockMvc.perform(get("/api/conta")
                .param("numeroConta", "2002"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroConta").value(2002))
                .andExpect(jsonPath("$.saldo").value(1000.00));
    }

    @Test
    void deveRetornar404QuandoContaNaoExiste() throws Exception {
        when(contaService.buscarPorNumero(9999)).thenThrow(new ContaNaoEncontradaException("Conta não encontrada"));

        mockMvc.perform(get("/api/conta")
                .param("numeroConta", "9999"))
                .andExpect(status().isNotFound());
    }
}
