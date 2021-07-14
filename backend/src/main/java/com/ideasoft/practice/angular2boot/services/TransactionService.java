package com.ideasoft.practice.angular2boot.services;

import com.ideasoft.practice.angular2boot.entities.CompanyBean;
import com.ideasoft.practice.angular2boot.entities.TransactionBean;
import com.ideasoft.practice.angular2boot.exceptions.TransactionValueBiggerThanCompanyBalanceException;
import com.ideasoft.practice.angular2boot.repositories.CompaniesRepository;
import com.ideasoft.practice.angular2boot.repositories.TransactionsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("http://localhost:4200")
@RequestMapping("/v1/transaction")
@RestController
public class TransactionService {
    private final CompaniesRepository companiesRepository;
    private final TransactionsRepository transactionsRepository;

    @Autowired
    public TransactionService(CompaniesRepository companiesRepository, TransactionsRepository transactionsRepository) {
        this.companiesRepository = companiesRepository;
        this.transactionsRepository = transactionsRepository;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @PostMapping(path = "/incoming", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public TransactionBean incomingTransaction(@RequestBody TransactionBean transaction) {
        return transactionsRepository.save(transaction);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @PostMapping(path = "/outgoing", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public TransactionBean outgoingTransaction(@RequestBody TransactionBean transaction) {
        CompanyBean company = companiesRepository.findById(transaction.getCompany().getId()).get();
        Double difference = company.getBalance() - transaction.getTransactionValue();
        if (difference < 0) {
            throw new TransactionValueBiggerThanCompanyBalanceException(company.getBalance(),
                    transaction.getTransactionValue());
        }
        transaction.setTransactionValue(transaction.getTransactionValue() * (-1));
        TransactionBean result = transactionsRepository.save(transaction);
        return result;
    }
}
