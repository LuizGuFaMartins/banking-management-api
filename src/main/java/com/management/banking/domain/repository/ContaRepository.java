package com.management.banking.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.banking.domain.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, UUID> {
    Optional<Conta> findByNumeroConta(Integer numeroConta);
}
