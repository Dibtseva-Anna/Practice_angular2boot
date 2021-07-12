package com.ideasoft.practice.angular2boot.transactions;

import com.ideasoft.practice.angular2boot.entities.CompanyBean;
import com.ideasoft.practice.angular2boot.repositories.CompaniesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/balance")
@RestController
public class BalanceService {
    @Autowired
    CompaniesRepository companiesRepository;

    @GetMapping
    public Iterable<CompanyBean> getCompanies(){
        return companiesRepository.findAll();
    }
}
