package com.ideasoft.practice.angular2boot.repositories;

import com.ideasoft.practice.angular2boot.entities.TransactionBean;
import org.springframework.data.repository.CrudRepository;

public interface TransactionsRepository extends CrudRepository<TransactionBean, Long> {}
