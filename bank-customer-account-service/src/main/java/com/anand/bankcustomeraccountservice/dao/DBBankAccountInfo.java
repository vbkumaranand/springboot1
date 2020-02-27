package com.anand.bankcustomeraccountservice.dao;

import com.anand.bankcustomeraccountservice.models.BankAccountDetail;
import org.springframework.data.repository.CrudRepository;

public interface DBBankAccountInfo extends CrudRepository<BankAccountDetail, Integer>{

}



