package com.ideasoft.practice.angular2boot.entities;

import javax.persistence.*;

@Entity
@Table
public class CompanyBean {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private Double balance;
}
