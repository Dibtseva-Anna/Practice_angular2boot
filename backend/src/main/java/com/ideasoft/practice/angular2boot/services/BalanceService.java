package com.ideasoft.practice.angular2boot.services;

import com.ideasoft.practice.angular2boot.entities.CompanyBean;
import com.ideasoft.practice.angular2boot.repositories.CompaniesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin("http://localhost:4200")
@RequestMapping("/v1/balance")
@RestController
@ApiIgnore
public class BalanceService {
    private final CompaniesRepository companiesRepository;

    @Autowired
    public BalanceService(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    @GetMapping(path = "", produces = "application/json")
    public Iterable<CompanyBean> getCompanies() {
        return companiesRepository.findAll();
    }
}
