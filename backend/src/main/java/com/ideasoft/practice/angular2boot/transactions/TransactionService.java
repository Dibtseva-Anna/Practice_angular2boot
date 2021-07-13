package com.ideasoft.practice.angular2boot.transactions;

import com.ideasoft.practice.angular2boot.entities.CompanyBean;
import com.ideasoft.practice.angular2boot.entities.TransactionBean;
import com.ideasoft.practice.angular2boot.repositories.CompaniesRepository;
import com.ideasoft.practice.angular2boot.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/transaction")
@RestController
public class TransactionService {
    private final CompaniesRepository companiesRepository;
    private final TransactionsRepository transactionsRepository;

    @Autowired
    public TransactionService(CompaniesRepository companiesRepository, TransactionsRepository transactionsRepository) {
        this.companiesRepository = companiesRepository;
        this.transactionsRepository = transactionsRepository;
    }


    @Transactional
    @PostMapping(path = "/income", consumes = "application/json", produces = "application/json")
    public TransactionBean incomeTransaction(@RequestBody TransactionBean transaction){
        CompanyBean company = companiesRepository.findById(transaction.getCompany().getId()).get();
        company.setBalance(company.getBalance() + transaction.getTransactionValue());
        companiesRepository.save(company);
        return transactionsRepository.save(transaction);
    }

    @Transactional
    @PostMapping(path = "/outcome", consumes = "application/json", produces = "application/json")
    public TransactionBean outcomeTransaction(@RequestBody TransactionBean transaction) {
        CompanyBean company = companiesRepository.findById(transaction.getCompany().getId()).get();
        transaction.setTransactionValue(transaction.getTransactionValue() * (-1));
        Double sum = company.getBalance() + transaction.getTransactionValue();
        if(sum < 0){
            throw new RuntimeException();
        }
        company.setBalance(sum);
        TransactionBean result = transactionsRepository.save(transaction);
        companiesRepository.save(company);
        return result;
    }
}
