package com.ideasoft.practice.angular2boot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class TransactionValueBiggerThanCompanyBalanceException extends RuntimeException {
    private Double companyBalance;
    private Double transactionValue;

    public TransactionValueBiggerThanCompanyBalanceException(Double companyBalance, Double transactionValue) {
        this.companyBalance = companyBalance;
        this.transactionValue = transactionValue;
    }

    @Override
    public void printStackTrace() {
        System.err.println("Outgoing transaction value " + transactionValue +
                " is bigger than company's balance: " + companyBalance);
        super.printStackTrace();
    }
}
