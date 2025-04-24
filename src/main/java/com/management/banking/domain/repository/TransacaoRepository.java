package com.management.banking.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.banking.domain.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {
}