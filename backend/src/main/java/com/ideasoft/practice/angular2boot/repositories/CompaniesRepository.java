package com.ideasoft.practice.angular2boot.repositories;


import com.ideasoft.practice.angular2boot.entities.CompanyBean;
import org.springframework.data.repository.CrudRepository;

public interface CompaniesRepository extends CrudRepository<CompanyBean, Long> {}
