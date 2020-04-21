package com.wallet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wallet.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
