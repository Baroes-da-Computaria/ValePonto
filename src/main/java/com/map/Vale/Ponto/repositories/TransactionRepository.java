package com.map.Vale.Ponto.repositories;

import com.map.Vale.Ponto.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}