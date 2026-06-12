package com.erp.integration.repository;

import com.erp.integration.entity.FinancialTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinancialTransactionRepository
        extends JpaRepository<FinancialTransaction, Long> {

    Optional<FinancialTransaction> findByTransactionId(
            String transactionId);
}