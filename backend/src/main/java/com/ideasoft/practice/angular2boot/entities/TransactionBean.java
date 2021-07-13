package com.ideasoft.practice.angular2boot.entities;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class TransactionBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_t")
    private Long id;

    @JoinColumn(name = "id_c")
    @ManyToOne(fetch = FetchType.LAZY)
    private CompanyBean company;

    @Column(name = "transaction_value")
    private Double transactionValue;

    public Long getId() {
        return id;
    }

    public CompanyBean getCompany() {
        return company;
    }

    public Double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(Double transactionValue) {
        this.transactionValue = transactionValue;
    }
}
