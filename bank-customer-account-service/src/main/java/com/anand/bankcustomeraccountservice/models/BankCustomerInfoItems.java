package com.anand.bankcustomeraccountservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter


public class BankCustomerInfoItems {
    public BankCustomerInfoItems() {
        //Default Constructor
    }


//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private String id;

    public String getStrCustomerName() {
        return strCustomerName;
    }

    public void setStrCustomerName(String strCustomerName) {
        this.strCustomerName = strCustomerName;
    }

    public String getStrCustomerCity() {
        return strCustomerCity;
    }

    public void setStrCustomerCity(String strCustomerCity) {
        this.strCustomerCity = strCustomerCity;
    }

    public Integer getIntCustomerId() {
        return intCustomerId;
    }

    public void setIntCustomerId(Integer intCustomerId) {
        this.intCustomerId = intCustomerId;
    }


    private String strCustomerName;

    private String strCustomerCity;

    private Integer intCustomerId;

    public BankCustomerInfoItems(String strCustomerName, String strCustomerCity, Integer intCustomerId) {
        this.strCustomerName = strCustomerName;
        this.strCustomerCity = strCustomerCity;
        this.intCustomerId = intCustomerId;
    }







}
