package com.anand.bankcustomertransactionservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
//@Table(name="BankAccountDetail")
//@Entity
public class BankAccountDetail {
   // @Id
    //@Column(name="CustomerId")
    private Integer intCustomerId;
    //@Column(name="AccountBalance")
    private Integer intAccountBalance;
    //@Column(name="AccountNumber")
    private Integer intAccountNumber;

    public Integer getIntCustomerId() {
        return intCustomerId;
    }

    public void setIntCustomerId(Integer intCustomerId) {
        this.intCustomerId = intCustomerId;
    }

    public Integer getIntAccountBalance() {
        return intAccountBalance;
    }

    public void setIntAccountBalance(Integer intAccountBalance) {
        this.intAccountBalance = intAccountBalance;
    }

    public Integer getIntAccountNumber() {
        return intAccountNumber;
    }

    public void setIntAccountNumber(Integer intAccountNumber) {
        this.intAccountNumber = intAccountNumber;
    }

    public BankAccountDetail(Integer intCustomerId, Integer intAccountBalance, Integer intAccountNumber) {
        this.intCustomerId = intCustomerId;
        this.intAccountBalance = intAccountBalance;
        this.intAccountNumber = intAccountNumber;
    }

    public BankAccountDetail() {

    }



}
