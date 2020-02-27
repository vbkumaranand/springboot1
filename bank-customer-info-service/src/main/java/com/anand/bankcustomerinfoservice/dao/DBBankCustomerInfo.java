package com.anand.bankcustomerinfoservice.dao;

import com.anand.bankcustomerinfoservice.models.BankCustomerInfoItems;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.repository.CrudRepository;

public interface DBBankCustomerInfo extends CrudRepository<BankCustomerInfoItems, String> {

}
