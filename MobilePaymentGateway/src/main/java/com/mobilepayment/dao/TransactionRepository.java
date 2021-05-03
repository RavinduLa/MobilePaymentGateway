package com.mobilepayment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobilepayment.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
