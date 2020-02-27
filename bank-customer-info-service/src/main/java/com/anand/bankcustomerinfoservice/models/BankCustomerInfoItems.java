package com.anand.bankcustomerinfoservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name="CustomerInfo")
public class BankCustomerInfoItems {
    public BankCustomerInfoItems() {
        //Default Constructor
    }

    @Id
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

    @Column(name="CustomerName")
    private String strCustomerName;
    @Column(name="CustomerCity")
    private String strCustomerCity;
    @Column(name="CustomerId")
    private Integer intCustomerId;

    public BankCustomerInfoItems(String strCustomerName, String strCustomerCity, Integer intCustomerId) {
        this.strCustomerName = strCustomerName;
        this.strCustomerCity = strCustomerCity;
        this.intCustomerId = intCustomerId;
    }







}
